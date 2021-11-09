package com.nh.interceptor;

import com.nh.exception.AuthException;
import com.nh.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {


    private final JwtToken jwtToken;

    //pre , post , after
    //pre -> controller 와 servlet 사이에서 동작
    //post -> controller 실행중 동작
    //after -> contorller 실행 후 동작

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle Working");

        Cookie access_token = WebUtils.getCookie(request, "access_token");
        Cookie refresh_token = WebUtils.getCookie(request, "refresh_token");
        String accessToken = (access_token == null)? null: access_token.getValue();
        String refreshToken = (refresh_token == null)? null: refresh_token.getValue();
        String checkToken = null;
        if(refreshToken!=null)
            checkToken = refreshToken;
        else if(accessToken !=null)
            checkToken = accessToken;

        if ("true".equals(request.getHeader("AJAX"))) { //ajax 요청일 경우에만 토큰검사함
            if(checkToken == null) throw new AuthException(ExceptionEnum.UNAUTHORIZED.getCode()); //토큰이 존재하지 않을경우


            //토큰이 존재 할 경우
            if (checkToken != null && checkToken.length() > 0) {
                if (checkToken == refreshToken) {
                    try {
                        jwtToken.parseJwtToken(checkToken);
                    } catch (Exception e) {
                        throw new AuthException(ExceptionEnum.REFRESHEXPIRED.getCode());//리프레시 토큰 마저 만료되었을 경우
                    }
                } else if (checkToken == accessToken) {
                    try {
                        jwtToken.parseJwtToken(checkToken);
                    } catch (Exception e) {
                        throw new AuthException(ExceptionEnum.ACCESSEXPIRED.getCode());//억세스 토큰이 만료되었을 경우
                    }
                }

            }
            return false;
        } else {
            String userId = jwtToken.getUserId(checkToken);
            request.setAttribute("userId" , userId);
        }

        return true;
    }
}
