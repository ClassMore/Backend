package com.dev.classmoa.exception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handler(MethodArgumentNotValidException ex){
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> map.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handler2(ConstraintViolationException ex){
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }
}
