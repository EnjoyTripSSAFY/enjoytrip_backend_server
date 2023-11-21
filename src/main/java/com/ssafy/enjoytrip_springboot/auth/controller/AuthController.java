package com.ssafy.enjoytrip_springboot.auth.controller;

import com.ssafy.enjoytrip_springboot.auth.dto.UserAuthDto;
import com.ssafy.enjoytrip_springboot.auth.dto.UserInfoDto;
import com.ssafy.enjoytrip_springboot.auth.dto.UserLoginResponseDto;
import com.ssafy.enjoytrip_springboot.auth.jwt.JwtUtil;
import com.ssafy.enjoytrip_springboot.auth.service.AuthServiceImpl;
import com.ssafy.enjoytrip_springboot.common.response.ErrorResponse;
import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "*")
public class AuthController {


    private final AuthServiceImpl authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody UserAuthDto userAuthDto, HttpServletResponse res, HttpServletRequest req) {

        System.out.println("userAuthDto = " + userAuthDto);
        UserAuthDto loginUser = authService.signin(userAuthDto.getUserId(), userAuthDto.getUserPassword());
        log.debug("loginUser = " + loginUser);

        UserLoginResponseDto authInfo = UserLoginResponseDto.builder()
                .accessToken(loginUser.getAccessToken())
                .refreshToken(loginUser.getRefreshToken())
                .info(jwtUtil.checkAndGetClaims(loginUser.getAccessToken())).build();

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.CREATED, "로그인 성공", authInfo));
    }

    @PostMapping(value = "/member/refresh", headers = "refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("refresh-token") String refreshToken, @RequestBody UserAuthDto userAuthDto, HttpServletResponse res) {

        System.out.println("controller refreshToken = " + userAuthDto.getRefreshToken());
        System.out.println("header data = " + refreshToken);

        userAuthDto.setRefreshToken(refreshToken);
        // refresh token이 valid 한지 점검
        jwtUtil.checkAndGetClaims(userAuthDto.getRefreshToken());

        // DB에 저장된 refresh 토큰의 정보가 전달된 토큰의 정보와 같은지 판단
        if (userAuthDto.getRefreshToken().equals(authService.getRefreshToken(userAuthDto.getUserId()))) {
            // 새로운 토큰의 발행 및 배포
            String accessToken =  jwtUtil.createAccessToken(userAuthDto.getUserId());

            UserLoginResponseDto authInfo = UserLoginResponseDto.builder()
                    .accessToken(accessToken)
                    .info(jwtUtil.checkAndGetClaims(accessToken)).build();

            return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.CREATED, "토큰 다시 발행 및 로그인 성공", authInfo));
        }
        else{
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("result", "실패");
            return ResponseEntity.ok().body(new ErrorResponse("실패", "토큰 재발행 및 로그인 실패", resultMap));
        }
    }

    @GetMapping("/member/logout/{userId}")
    public ResponseEntity<?> logout(@PathVariable("userId") String userId) {
        log.debug("logout: {}", userId);
        authService.logout(userId);
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "로그아웃 성공"));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<?> getInfo(@PathVariable("userId") String userId) {
        System.out.println("getInfo!!!");
        UserInfoDto result = authService.getInfo(userId);
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "유저 조회 성공", result));
    }
}