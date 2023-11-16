package com.ssafy.enjoytrip_springboot.plan.common.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripPlanDto {

    private Long no;
    private Long userNo;
    private String title;
    private String describ;
    private Timestamp createdTime;
}
