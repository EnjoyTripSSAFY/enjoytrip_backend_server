package com.ssafy.enjoytrip_springboot.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserAuthDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userPassword")
    private String userPassword;

    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("refreshToken")
    private String refreshToken;
}
