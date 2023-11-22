//package com.ssafy.enjoytrip_springboot.auth.jwt.legacy;
//
//import io.jsonwebtoken.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.time.Duration;
//import java.util.Date;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.salt}")
//    private String salt;
//
//    @Value("#{${jwt.access-token-expmin}}")
//    private Long accessTokenExpireMin;
//
////    private Long accessTokenExpireMin = Duration.ofSeconds(10).toMillis();
//
//    @Value("#{${jwt.refresh-token-expmin}}")
//    private Long refreshTokenExpireMin;
//
//    public String createAccessToken(String userId) {
//        return create(userId, "accessToken", accessTokenExpireMin);
//    }
//
//    /**
//     * Refresh 토큰을 생성
//     * 인증을 위한 정보는 유지하지 않음
//     */
//    public String createRefreshToken() {
//        return create(null, "refreshToken", refreshTokenExpireMin);
//    }
//
//    /**
//     * 로그인 성공 시 사용자 정보를 기반으로 JWTToken을 생성해서 반환한다.
//     * JWT Token = Header + Payload + Signagure
//     */
//    private String create(String userId, String subject, long expireMin) {
//        final JwtBuilder builder = Jwts.builder();
//        // Header 설정
//        //builder.setHeaderParam("typ", "JWT");// 토큰의 타입으로 고정 값
//        // Payload 설정 - claim 정보 포함
//        builder.setSubject(subject)// 토큰 제목 설정
//                .setExpiration(new Date(System.currentTimeMillis() + expireMin));// 유효기간
//        // 담고 싶은 정보 설정
//        if (userId != null) {
//            builder.claim("user", userId);
//        }
//
//        // signature - secret key를 이용한 암호화
//        builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
//
//        // 마지막 직렬화 처리
//        final String jwt = builder.compact();
//        log.debug("토큰 발행: {}", jwt);
//        return jwt;
//    }
//
//    /**
//     * jwt 토큰을 분석해서 필요한 정보를 반환한다.
//     * 토큰에 문제가 있다면 RuntimeException을 발생시킨다.
//     */
//    public Map<String, Object> checkAndGetClaims(final String jwt) {
//        Jws<Claims> claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
//        log.trace("claims: {}", claims);
//        // Claims는 Map의 구현체이다.
//        return claims.getBody();
//    }
//
//}
