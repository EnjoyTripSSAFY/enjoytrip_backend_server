package com.ssafy.enjoytrip_springboot.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoDto {

    private String userId;
    private String userPassword;
    private String userName;
    private String email_id;
    private String email_domain;
    private Timestamp joinedTime;
    private String userRole;
}
