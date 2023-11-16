package com.ssafy.enjoytrip_springboot.member.query.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMemberDto {

    @ApiModelProperty(value = "유저 아이디", required = true)
    private String userId;
    @ApiModelProperty(value = "비밀번호", required = true)
    private String userPassword;
    @ApiModelProperty(value = "이름", required = true)
    private String userName;
    @ApiModelProperty(value = "이메일 id", required = true)
    private String emailId;
    @ApiModelProperty(value = "이메일 domain", required = true)
    private String email_domain;
    @ApiModelProperty(value = "가입일", required = true)
    private String joinedTime;

}
