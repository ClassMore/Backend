package com.dev.classmoa.exception.advice;

import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.dto.ErrorResult;
import com.dev.classmoa.exception.type.ClassmoaErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExHandlerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResult> commonHandler(ClassmoaException ex) {
        ClassmoaErrorCode classmoaErrorCode = ex.getClassmoaErrorCode();
        ErrorResult result = ErrorResult.builder()
                .httpStatus(classmoaErrorCode.getHttpStatus().name())
                .message(classmoaErrorCode.getMessage())
                .statusCode(classmoaErrorCode.getHttpStatus().value())
                .build();

        return new ResponseEntity<>(result, classmoaErrorCode.getHttpStatus());
    }
}
