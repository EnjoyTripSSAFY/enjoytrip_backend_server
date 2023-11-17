package com.ssafy.enjoytrip_springboot.auth.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // preflight를 위한 OPTIONS 요청은 그냥 전달
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }
        // request의 헤더에서 access-token으로 넘어온 정보를 찾음
        String accessToken = request.getHeader("access-token");
        log.debug("경로: {}, 토큰: {}", request.getServletPath(), accessToken);

        if (accessToken != null) {
            // 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생시킨다.
            jwtUtil.checkAndGetClaims(accessToken);
            return true;
        } else {
            throw new RuntimeException("인증 토큰이 없습니다.");
        }
    }
}