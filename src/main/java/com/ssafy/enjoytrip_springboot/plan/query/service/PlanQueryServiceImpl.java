package com.ssafy.enjoytrip_springboot.plan.query.service;

import com.ssafy.enjoytrip_springboot.plan.common.exception.PlanException;
import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listPlanPerDateJoinDetailList;
import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listTripPlanResDto;
import com.ssafy.enjoytrip_springboot.plan.query.mapper.PlanQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlanQueryServiceImpl implements PlanQueryService{

    private final PlanQueryMapper planQueryMapper;

    @Override
    public List<listTripPlanResDto> listTripPlan(Long userNo) {

        try {
            List<listTripPlanResDto> resultList = planQueryMapper.listTripPlan(userNo);

            return resultList;
        } catch (Exception e){
            throw new PlanException("여행 계획 리스트 조회 실패");
        }
    }

    @Override
    public List<listPlanPerDateJoinDetailList> listPlanPerDateAndDetail(Long tripPlanNo) {

        try {
            List<listPlanPerDateJoinDetailList> resultList = planQueryMapper.listPlanPerDateAndDetail(tripPlanNo);
            return resultList;

        } catch (Exception e){
            throw new PlanException("세부 계획 조회 실패");
        }
    }
}
