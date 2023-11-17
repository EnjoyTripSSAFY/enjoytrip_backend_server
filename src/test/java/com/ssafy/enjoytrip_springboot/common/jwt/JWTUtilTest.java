package com.ssafy.enjoytrip_springboot.common.jwt;

import com.ssafy.enjoytrip_springboot.auth.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class JWTUtilTest {
    @Autowired
    JwtUtil util;

    @Test
    public void tokenGenTest() {
        // given
        String userId = "sanghak";
        // when
        String token = util.createAccessToken(userId);
        // then
        assertNotNull(token);
        log.info("token : " + token);
    }
}
