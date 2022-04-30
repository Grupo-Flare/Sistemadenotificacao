package br.com.flare.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@ControllerAdvice
public class ApiErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ApiErrorsOutput handleValidationError(MethodArgumentNotValidException exception) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public @ResponseBody ApiErrorsOutput handleValidationError(BindException exception) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);

    }

    @ExceptionHandler(ApiErrorException.class)
    public @ResponseBody ResponseEntity<ApiErrorsOutput> handleApiErrorException(ApiErrorException exception) {

        ApiErrorsOutput outputDto = new ApiErrorsOutput();
        outputDto.addError(exception.getReason());

        return ResponseEntity.status(exception.getHttpStatus()).body(outputDto);

    }

    @ExceptionHandler(MvcErrorException.class)
    public ModelAndView handleMvcErrorException(MvcErrorException exception) {
        exception.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("status", exception.getHttpStatus());
        modelAndView.addObject("reason", exception.getReason());
        modelAndView.setStatus(exception.getHttpStatus());
        return modelAndView;
    }

    private ApiErrorsOutput buildValidationErrors(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {

        ApiErrorsOutput validationErrors = new ApiErrorsOutput();

        globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });

        return validationErrors;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}
