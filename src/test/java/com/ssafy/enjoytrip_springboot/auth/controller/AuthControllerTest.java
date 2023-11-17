package com.ssafy.enjoytrip_springboot.auth.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ssafy.enjoytrip_springboot.auth.dto.UserLoginRequestDto;
import com.ssafy.enjoytrip_springboot.auth.jwt.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class AuthControllerTest {

    @Autowired
    MockMvc mock;

    @Autowired
    JwtUtil util;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("정보를 전달해서 로그인 후 토큰을 잘 얻을 수 있는지 확인한다.")
    public void testLogin() throws Exception {
        // given
        UserLoginRequestDto userLoginRequestDto = UserLoginRequestDto.builder()
                .userId("ssafy1")
                .userPassword("1234").build();

        String content = objectMapper.writeValueAsString(userLoginRequestDto);

        // when
        MockHttpServletRequestBuilder reqBuilder
                = post("/auth/member/login").contentType("application/json").content(content);
        ResultActions action = mock.perform(reqBuilder);

        // then
        action.andExpect(status().is(200));

    }

    @Test
    @DisplayName("정상적인 토큰을 전달했을 때 원하는 정보를 잘 얻을 수 있는지 확인한다.")
    public void testGetInfoSuccess() throws Exception {
        // given
        String token = util.createAccessToken("ssafy1");

        // when
        MockHttpServletRequestBuilder reqBuilder
                = get("/auth/info").header("access-token", token);
        ResultActions action = mock.perform(reqBuilder);

        // then
        action.andExpect(status().is(202));
    }

    @Test
    @DisplayName("비 정상적인 토큰을 전달했을 때 예외가 발생하는지 확인한다.")
    public void testGetInfoFail() throws Exception {
        // given
        String token = "fail token";

        // when
        MockHttpServletRequestBuilder reqBuilder
                = get("/auth/info").header("access-token", token);

        // then
        assertThrows(Exception.class, () -> {
            mock.perform(reqBuilder);
        });
    }
}