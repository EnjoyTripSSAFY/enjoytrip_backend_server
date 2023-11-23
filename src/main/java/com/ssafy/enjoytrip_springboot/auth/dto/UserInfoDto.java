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

    private Long no;
    private String userId;
    private String userPassword;
    private String userName;
    private String emailId;
    private String emailDomain;
    private Timestamp joinedTime;
    private String roles;
}
