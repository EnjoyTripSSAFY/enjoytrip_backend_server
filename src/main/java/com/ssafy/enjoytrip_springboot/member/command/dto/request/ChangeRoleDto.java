package com.ssafy.enjoytrip_springboot.member.command.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeRoleDto {

    private String userId;
    private String roles;
}
