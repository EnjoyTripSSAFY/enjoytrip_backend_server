package com.ssafy.enjoytrip_springboot.plan.command.controller;

import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.plan.command.dto.CreateTripPlanAndPlanPerDateAndDetailPlanDto;
import com.ssafy.enjoytrip_springboot.plan.command.service.PlanCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
public class PlanCommandController {

    private final PlanCommandService planCommandService;

    @PostMapping
    public ResponseEntity<?> createTripPlanAndPlanPerDateAndDetailPlan(@RequestBody CreateTripPlanAndPlanPerDateAndDetailPlanDto createTripPlanDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        int result = planCommandService.createTripPlanAndPlanPerDateAndDetailPlan(createTripPlanDto);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.CREATED, "여행 계획 생성 성공", result));
    }

}
