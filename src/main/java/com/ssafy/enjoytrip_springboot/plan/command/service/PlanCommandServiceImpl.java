package com.ssafy.enjoytrip_springboot.plan.command.service;

import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateDetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreatePlanPerDateDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateTripPlanAndPlanPerDateAndDetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateTripPlanDto;
import com.ssafy.enjoytrip_springboot.plan.command.mapper.PlanCommandMapper;
import com.ssafy.enjoytrip_springboot.plan.common.exception.PlanException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PlanCommandServiceImpl implements PlanCommandService{

    private final PlanCommandMapper planCommandMapper;

    @Override
    public int createTripPlanAndPlanPerDateAndDetailPlan(CreateTripPlanAndPlanPerDateAndDetailPlanDto createTripPlanAndPlanPerDateAndDetailPlanDto) {

        try{
            // 여행 계획 데이터 생성 로직
            CreateTripPlanDto tripPlanDto = CreateTripPlanDto.builder()
                    .title(createTripPlanAndPlanPerDateAndDetailPlanDto.getTitle())
                    .describ(createTripPlanAndPlanPerDateAndDetailPlanDto.getDescrib())
                    .userNo(createTripPlanAndPlanPerDateAndDetailPlanDto.getUserNo()).build();

            System.out.println("tripPlanDto : " + tripPlanDto.toString());
            int createTripPlanResult = createTripPlan(tripPlanDto);
            System.out.println("createTripPlanResult : " + createTripPlanResult);

            if(createTripPlanResult == 0){
                throw new PlanException("trip plan 생성 중 에러");
            }

            // 날짜별 계획 데이터 생성 로직
            List<CreatePlanPerDateDto> planPerDateDtoList = createTripPlanAndPlanPerDateAndDetailPlanDto.getPlanPerDateList();

            int createPlanPerDateResult = 0;
            for(CreatePlanPerDateDto ppd : planPerDateDtoList) {
                createPlanPerDateResult = createPlanPerDate(ppd);
                System.out.println("createPlanPerDateResult : " + createPlanPerDateResult);

                if(createPlanPerDateResult == 0) {
                    throw new PlanException("날짜별 계획 데이터 생성 중 에러");
                }
            }

            // 세부 계획 데이터 생성 로직
            List<CreateDetailPlanDto> detailPlanDtoList = createTripPlanAndPlanPerDateAndDetailPlanDto.getDetailPlanList();

            int createDetailPlanResult = 0;
            for(CreateDetailPlanDto dp : detailPlanDtoList) {
                createDetailPlanResult = createDetailPlan(dp);
                System.out.println("createDetailPlanResult : " + createDetailPlanResult);

                if(createDetailPlanResult == 0) {
                    throw new PlanException("세부 계획 데이터 생성 중 에러");
                }
            }

            return createTripPlanResult + createPlanPerDateResult + createDetailPlanResult;

        } catch(Exception e) {
            throw new PlanException("계획 생성 중 에러 발생");
        }
    }

    @Override
    public int createTripPlan(CreateTripPlanDto tripPlanDto) {

        try{
            int createTripPlanResult = planCommandMapper.createTripPlan(tripPlanDto);
            System.out.println("createTripPlanResult : " + createTripPlanResult);
            return createTripPlanResult;

        } catch (SQLException e){
            throw new PlanException("trip plan 생성 중 에러");
        }
    }

    @Override
    public int createPlanPerDate(CreatePlanPerDateDto planPerDateDto) {

        try{
            int createPlanPerDateResult = planCommandMapper.createPlanPerDate(planPerDateDto);
            return createPlanPerDateResult;

        } catch(SQLException e) {
            throw new PlanException("날짜별 계획 데이터 생성 중 에러");
        }
    }

    @Override
    public int createDetailPlan(CreateDetailPlanDto detailPlanDto) {
        try{
            int createDetailPlanResult = planCommandMapper.createDetailPlan(detailPlanDto);
            return createDetailPlanResult;

        } catch(SQLException e) {
            throw new PlanException("세부 계획 데이터 생성 중 에러");
        }
    }
}
