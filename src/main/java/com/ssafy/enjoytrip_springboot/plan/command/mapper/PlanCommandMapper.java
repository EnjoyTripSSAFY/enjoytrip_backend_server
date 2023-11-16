package com.ssafy.enjoytrip_springboot.plan.command.mapper;

import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateDetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreatePlanPerDateDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateTripPlanDto;
import com.ssafy.enjoytrip_springboot.plan.common.dto.DetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.common.dto.PlanPerDateDto;
import com.ssafy.enjoytrip_springboot.plan.common.dto.TripPlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface PlanCommandMapper {


    int createTripPlan(CreateTripPlanDto createTripPlanDto) throws SQLException;

    int createPlanPerDate(CreatePlanPerDateDto createPlanPerDateDto) throws SQLException;

    int createDetailPlan(CreateDetailPlanDto createDetailPlanDto) throws SQLException;
}
