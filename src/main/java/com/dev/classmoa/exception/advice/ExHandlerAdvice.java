package com.dev.classmoa.exception.advice;

import com.dev.classmoa.exception.ClassmoaException;
import com.dev.classmoa.exception.dto.ErrorResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExHandlerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResult> commonHandler(ClassmoaException ex) {
        ErrorResult result = ErrorResult.builder()
                .httpStatus(ex.getHttpStatus().name())
                .message(ex.getMessage())
                .statusCode(ex.getHttpStatus().value())
                .build();

        return ResponseEntity.ok(result);
    }
}
