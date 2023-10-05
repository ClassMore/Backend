package com.dev.classmoa.exception;

import org.springframework.http.HttpStatus;

public class ClassmoaException extends RuntimeException {
    private HttpStatus httpStatus;

    public ClassmoaException(String message){
        super(message);
    }

    public ClassmoaException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public ClassmoaException(String message, Throwable cause) {
        super(message, cause);

    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }
}
