package com.ssafy.enjoytrip_springboot.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaveRefreshTokenDto {

    private String userId;
    private String refreshToken;
}
