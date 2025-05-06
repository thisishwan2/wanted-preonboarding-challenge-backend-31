package com.example.cqrs_challenge.global.exception;

import com.example.cqrs_challenge.global.response.ErrorCode;
import lombok.Getter;

import java.util.Map;

/**
 * 커스텀 에외 클래스. 해당 클래스로 예외를 던져서 예외처리를 한다.
 */
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;
    private final Map<String, Object> details;

    public CustomException(ErrorCode errorCode, String message, Map<String, Object> details) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }
}
