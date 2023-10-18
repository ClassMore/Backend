package com.dev.classmoa.exception.advice;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handler(ConstraintViolationException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("에러 메세지", ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handler2(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> map.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(map);
    }
}
