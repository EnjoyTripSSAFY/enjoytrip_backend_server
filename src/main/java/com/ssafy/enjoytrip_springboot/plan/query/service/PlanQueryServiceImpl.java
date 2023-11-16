package com.ssafy.enjoytrip_springboot.plan.query.service;

import com.ssafy.enjoytrip_springboot.plan.common.exception.PlanException;
import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listTripPlanResDto;
import com.ssafy.enjoytrip_springboot.plan.query.mapper.PlanQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
