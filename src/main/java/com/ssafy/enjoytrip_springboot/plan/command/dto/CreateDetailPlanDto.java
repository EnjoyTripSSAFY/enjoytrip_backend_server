package com.ssafy.enjoytrip_springboot.plan.command.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateDetailPlanDto {

    private Long planPerDateNo;
    private String attractionName;
    private int cost;
    private String startTime;
    private String endTime;
    private Long contentId;
}
