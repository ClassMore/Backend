package com.dev.classmoa.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClassmoaErrorCode {

    NOT_FOUND_LECTURE(HttpStatus.NOT_FOUND, "존재 하지 않는 강의 입니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "존재 하지 않는 회원 입니다."),
    NOT_FOUND_OPINION(HttpStatus.NOT_FOUND, "존재 하지 않는 의견 입니다."),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "존재 하지 않는 댓글 입니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 생겼습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
