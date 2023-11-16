package com.ssafy.enjoytrip_springboot.member.command.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {


    @ApiModelProperty(value = "유저 아이디", required = true)
    private String userId;
    @ApiModelProperty(value = "이름", required = true)
    private String userName;
    @ApiModelProperty(value = "이메일 id", required = true)
    private String emailId;
    @ApiModelProperty(value = "이메일 domain", required = true)
    private String email_domain;
    @ApiModelProperty(value = "가입일", required = true)
    private String joinDate;

}
