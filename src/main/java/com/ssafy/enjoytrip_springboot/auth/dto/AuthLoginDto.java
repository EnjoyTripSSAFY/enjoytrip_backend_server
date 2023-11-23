package com.ssafy.enjoytrip_springboot.auth.dto;

import lombok.Data;

@Data
public class AuthLoginDto {
    private String userId;
    private String userPassword;
}