package com.nh;

import com.nh.jwt.JwtToken;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@Slf4j
public class JwtTest extends TestCase {


    @Test
    public void testTime() {
        JwtToken jwtToken = new JwtToken();
        String accessToken = jwtToken.makeJwtToken("test1", 0);
        String refreshToken = jwtToken.makeJwtToken("test1", 1);

        Long expiredTime = jwtToken.getExpiredTime(accessToken);
        log.info(String.valueOf(expiredTime));
    }
}
