package com.nh.interceptor;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ExceptionResponse {
    private final int status;
    private final String code;
    private final String message;

    public static ResponseEntity<ExceptionResponse> toResponseEntity(ExceptionEnum exceptionEnum) {
        return ResponseEntity
                .status(exceptionEnum.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .code(exceptionEnum.getCode())
                        .message(exceptionEnum.getMessage())
                        .build()
                );
    }
}
