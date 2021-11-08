package com.nh.interceptor;

import com.nh.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class InterceptorControllerAdvice {
    //인터셉터에서 커스텀으로만든 AuthException 의 핸들링 처리

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> handlerException(AuthException e) {
        ExceptionEnum result = ExceptionEnum.getExceptionStatus(e.getMessage());
        return ExceptionResponse.toResponseEntity(result);
    }




}
