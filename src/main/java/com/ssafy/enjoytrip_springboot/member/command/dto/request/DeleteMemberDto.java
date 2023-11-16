package com.ssafy.enjoytrip_springboot.member.command.dto.request;

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
public class DeleteMemberDto {

    @ApiModelProperty(value = "유저 아이디", required = true)
    private String userId;
}

