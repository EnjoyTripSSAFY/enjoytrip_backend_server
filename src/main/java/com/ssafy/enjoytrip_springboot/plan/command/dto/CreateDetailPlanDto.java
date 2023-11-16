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
    private int cost;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long contentId;
}
