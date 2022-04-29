package br.com.flare.exceptionHandler;

import org.springframework.http.HttpStatus;

public class MvcErrorException extends ApiErrorException {

    public MvcErrorException(HttpStatus httpStatus, String reason) {
        super(httpStatus, reason);
    }

}
