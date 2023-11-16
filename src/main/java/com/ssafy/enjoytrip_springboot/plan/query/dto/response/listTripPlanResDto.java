package com.ssafy.enjoytrip_springboot.plan.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class listTripPlanResDto {

    private Long no;
    private String title;
    private String describ;
}
