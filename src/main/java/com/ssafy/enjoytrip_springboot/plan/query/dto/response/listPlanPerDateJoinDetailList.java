package com.ssafy.enjoytrip_springboot.plan.query.dto.response;

import com.ssafy.enjoytrip_springboot.plan.common.dto.DetailPlanDto;
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
public class listPlanPerDateJoinDetailList {

    private Long no;
    private Long tripPlanNo;
    private Timestamp planTime;
    private List<DetailPlanDto> detailPlans;
}
