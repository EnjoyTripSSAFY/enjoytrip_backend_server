package com.ssafy.enjoytrip_springboot.plan.command.service;

import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateDetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreatePlanPerDateDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateTripPlanAndPlanPerDateAndDetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateTripPlanDto;
import com.ssafy.enjoytrip_springboot.plan.common.dto.DetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.common.dto.PlanPerDateDto;
import com.ssafy.enjoytrip_springboot.plan.common.dto.TripPlanDto;

public interface PlanCommandService {
    int createTripPlanAndPlanPerDateAndDetailPlan(CreateTripPlanAndPlanPerDateAndDetailPlanDto createTripPlanDto);

    int createTripPlan(CreateTripPlanDto tripPlanDto);

    int createPlanPerDate(CreatePlanPerDateDto planPerDateDto);

    int createDetailPlan(CreateDetailPlanDto detailPlanDto);
}
