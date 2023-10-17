package com.dev.classmoa.exception;

import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import org.springframework.http.HttpStatus;

public class ClassmoaException extends RuntimeException {
    private HttpStatus httpStatus;
    private ClassmoaErrorCode classmoaErrorCode;

    public ClassmoaException(String message){
        super(message);
    }

    public ClassmoaException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public ClassmoaException(ClassmoaErrorCode classmoaErrorCode){
        this.classmoaErrorCode = classmoaErrorCode;
    }

    public ClassmoaException(String message, Throwable cause) {
        super(message, cause);

    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }

    public ClassmoaErrorCode getClassmoaCode(){
        return this.classmoaErrorCode;
    }
}
