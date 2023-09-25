package com.example.classmoa.exception;

public class ClassmoaException extends RuntimeException{
    private LectureCode classmoaCode;
    public ClassmoaException(String message, Throwable cause){
        super(message, cause);
    }

    public ClassmoaException(LectureCode classmoaCode){
        this.classmoaCode = classmoaCode;
    }

}
