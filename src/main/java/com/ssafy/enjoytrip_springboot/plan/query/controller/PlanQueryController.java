package com.ssafy.enjoytrip_springboot.plan.query.controller;

import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listPlanPerDateJoinDetailList;
import com.ssafy.enjoytrip_springboot.plan.query.dto.response.listTripPlanResDto;
import com.ssafy.enjoytrip_springboot.plan.query.service.PlanQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
public class PlanQueryController {

    private final PlanQueryService planQueryService;

    // 유저가 등록한 모든 여행계획 리스트
    @GetMapping("/{userNo}")
    public ResponseEntity<?> listTripPlan(@PathVariable("userNo") Long userNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<listTripPlanResDto> resultList = planQueryService.listTripPlan(userNo);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "여행계획 리스트 조회 성공", resultList));
    }

    @GetMapping("/getNo")
    public ResponseEntity<?> getBiggestPlanPerDateNo() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Long biggestPlanPerDateNo = planQueryService.getBiggestPlanPerDateNo();

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "가장 큰 일자별 여행 번호 + 1 반환", biggestPlanPerDateNo));
    }

    @GetMapping("/detail/{tripPlanNo}")
    public ResponseEntity<?> listPlanPerDateAndDetail(@PathVariable("tripPlanNo") Long tripPlanNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<listPlanPerDateJoinDetailList> resultList = planQueryService.listPlanPerDateAndDetail(tripPlanNo);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "여행 세부 정보 조회 성공", resultList));
    }

}