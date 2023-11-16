package com.ssafy.enjoytrip_springboot.member.command.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberDto {

    @ApiModelProperty(value = "유저 아이디", required = true)
    private String userId;
    @ApiModelProperty(value = "이름", required = false)
    private String userName;
    @ApiModelProperty(value = "비밀번호", required = false)
    private String userPassword;
    @ApiModelProperty(value = "이메일 id", required = false)
    private String emailId;
    @ApiModelProperty(value = "이메일 domain", required = false)
    private String emailDomain;

}

