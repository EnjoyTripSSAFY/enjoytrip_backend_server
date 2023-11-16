package com.ssafy.enjoytrip_springboot.member.command.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @ApiModelProperty(value = "유저 아이디", required = true)
    private String userId;
    @ApiModelProperty(value = "유저 비밀번호", required = true)
    private String userPassword;
}