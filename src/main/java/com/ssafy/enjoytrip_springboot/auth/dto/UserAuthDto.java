package com.ssafy.enjoytrip_springboot.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserAuthDto {

    private String userId;
    private String userPassword;
    private String accessToken;
    private String refreshToken;
}
