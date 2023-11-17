package com.ssafy.enjoytrip_springboot.auth.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserLoginRequestDto {

    private String userId;
    private String userPassword;
}
