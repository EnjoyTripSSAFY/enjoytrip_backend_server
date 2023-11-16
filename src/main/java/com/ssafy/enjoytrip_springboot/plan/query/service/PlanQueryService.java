package com.ssafy.enjoytrip_springboot.plan.query.service;

import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listPlanPerDateJoinDetailList;
import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listTripPlanResDto;

import java.util.List;

public interface PlanQueryService {


    List<listTripPlanResDto> listTripPlan(Long userNo);

    List<listPlanPerDateJoinDetailList> listPlanPerDateAndDetail(Long tripPlanNo);
}
