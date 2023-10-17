package com.dev.classmoa.exception.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ErrorResult {
    private String httpStatus;
    private String message;
    private int statusCode;

    @Builder
    public ErrorResult(String httpStatus, String message, int statusCode) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.statusCode = statusCode;
    }
}
