package com.ssafy.enjoytrip_springboot.plan.command.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateTripPlanDto {

    private Long userNo;
    private String title;
    private String describ;
}
