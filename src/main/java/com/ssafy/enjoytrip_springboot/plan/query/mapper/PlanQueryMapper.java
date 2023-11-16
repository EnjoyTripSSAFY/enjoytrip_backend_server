package com.ssafy.enjoytrip_springboot.plan.query.mapper;

import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listTripPlanResDto;

import java.util.List;

public interface PlanQueryMapper {

    List<listTripPlanResDto> listTripPlan(Long userNo);

}
