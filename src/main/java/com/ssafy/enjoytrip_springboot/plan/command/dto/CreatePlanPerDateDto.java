package com.ssafy.enjoytrip_springboot.plan.command.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatePlanPerDateDto {

    private Long tripPlanNo;
    private Timestamp planTime;

}
