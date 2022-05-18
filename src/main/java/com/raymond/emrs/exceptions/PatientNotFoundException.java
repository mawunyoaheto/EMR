package com.raymond.emrs.exceptions;

import org.springframework.http.HttpStatus;

public class PatientNotFoundException extends RuntimeException{
    private HttpStatus httpStatus;

    public PatientNotFoundException(long id, HttpStatus httpStatus) {
        super("Could not find patient "+id);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
