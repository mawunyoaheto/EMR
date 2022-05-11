package com.raymond.emrs.exceptions;

import org.springframework.http.HttpStatus;

public class EmrException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public EmrException(String message) {
        super();
        this.message = message;
    }

    public EmrException(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
