package com.ssafy.enjoytrip_springboot.plan.command.dto;

import com.ssafy.enjoytrip_springboot.plan.common.dto.DetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.common.dto.PlanPerDateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTripPlanAndPlanPerDateAndDetailPlanDto {

    private Long userNo;
    private String title;
    private String describ;

    List<CreatePlanPerDateDto> planPerDateList;
    List<CreateDetailPlanDto> detailPlanList;
}
