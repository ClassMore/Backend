package com.dev.classmoa.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LectureCode {

    NOT_FOUND_LECTURE(HttpStatus.NOT_FOUND, "찾는 강의가 없습니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 생겼습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
