package com.ssafy.enjoytrip_springboot.plan.common.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanPerDateDto {

    private Long no;
    private Long tripPlanNo;
    private Timestamp planTime;
}
