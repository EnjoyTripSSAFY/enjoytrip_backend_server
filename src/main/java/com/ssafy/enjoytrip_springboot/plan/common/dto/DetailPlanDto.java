package com.ssafy.enjoytrip_springboot.plan.common.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailPlanDto {

    private Long no;
    private Long planPerDateNo;
    private String attractionName;
    private int cost;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long contentId;
}
