package com.ssafy.enjoytrip_springboot.plan.query.mapper;

import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listPlanPerDateJoinDetailList;
import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listTripPlanResDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlanQueryMapper {

    List<listTripPlanResDto> listTripPlan(Long userNo) throws SQLException;

    List<listPlanPerDateJoinDetailList> listPlanPerDateAndDetail(Long tripPlanNo) throws SQLException;

    Long getBiggestPlanPerDateNo();
}
