package com.nh.interceptor;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"E0001", "권한이 없습니다."),
    EXPIREDTOKEN(HttpStatus.BAD_REQUEST,"E0002", "만료된 토큰입니다."),
    COUNTERFEIT(HttpStatus.INTERNAL_SERVER_ERROR, "E0003", "위조시도");

    private HttpStatus httpStatus;
    private String  code;
    private String message;

    ExceptionEnum(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public static ExceptionEnum getExceptionStatus(String code) {
        ExceptionEnum returnEnum = null;
        if (code.equals("E0001")) {
            returnEnum = UNAUTHORIZED;
        } else if (code.equals("E0002")) {
            returnEnum = EXPIREDTOKEN;
        } else{
            returnEnum = COUNTERFEIT;
        }

        return returnEnum;
    }



}
