package com.ssafy.enjoytrip_springboot.auth.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserLoginResponseDto {

    private String accessToken;
    private String refreshToken;
    private Map<String, Object> info;
}
