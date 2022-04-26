package br.com.flare.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class ApiErrorsOutput {
    private final List<String> globalErrorMessages = new ArrayList<>();
    private final List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();


    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutputDto> getFieldErrors() {
        return fieldErrors;
    }
}

class FieldErrorOutputDto {

    private final String field;
    private final String message;

    public FieldErrorOutputDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}