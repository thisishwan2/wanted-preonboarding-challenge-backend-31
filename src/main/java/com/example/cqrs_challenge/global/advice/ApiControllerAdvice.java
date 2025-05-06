package com.example.cqrs_challenge.global.advice;

import com.example.cqrs_challenge.global.exception.CustomException;
import com.example.cqrs_challenge.global.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 글로벌 예외 처리 Advice. CustomException 발생시 응답 생성
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.example.cqrs_challenge")
public class ApiControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ApiErrorResponse handleCustomException(CustomException e) {
        return ApiErrorResponse.builder()
                .success(false)
                .error(ApiErrorResponse.ErrorDetail.builder()
                        .code(e.getErrorCode())
                        .message(e.getMessage())
                        .details(e.getDetails())
                        .build())
                .build();
    }
}
