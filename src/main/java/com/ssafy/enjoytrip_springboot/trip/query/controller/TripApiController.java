package com.ssafy.enjoytrip_springboot.trip.query.controller;

import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.trip.common.dto.*;
import com.ssafy.enjoytrip_springboot.trip.query.service.AttractionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = { "3-1. TripApiController" })
@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
@Slf4j
public class TripApiController{
	private final AttractionService service;

	@GetMapping("/city")
	public ResponseEntity getCityList(int areaCode) {
		List<RegionResponse> city = service.cityList(areaCode)
				.stream()
				.map(GugunDto::parseToResponse)
				.collect(Collectors.toList());

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", city);

		SuccessResponse response = SuccessResponse.builder()
				.httpStatus(HttpStatus.OK)
				.msg("정상적으로 값이 반환되었습니다.")
				.result(resultMap)
				.build();

		return ResponseEntity.ok(response);

	}

	@GetMapping("/state")
	public ResponseEntity<SuccessResponse> getStateList() {
		List<SidoDto> collect = service.stateList();

		log.info(collect.toString());


		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", collect);

		SuccessResponse response = SuccessResponse.builder()
				.httpStatus(HttpStatus.OK)
				.msg("정상적으로 값이 반환되었습니다.")
				.result(resultMap)
				.build();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/triplist")
	public ResponseEntity<SuccessResponse> getTripList(AttractionRequest request){

		// 리스트로 변환
		List<AttractionResponse> collect = service.attractionList(request)
				.stream()
				.map(AttractionInfoDto::parseToResponse)
				.collect(Collectors.toList());

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", collect);

		SuccessResponse response = SuccessResponse.builder()
				.httpStatus(HttpStatus.OK)
				.msg("정상적으로 값이 반환되었습니다.")
				.result(resultMap)
				.build();

		return ResponseEntity.ok(response);
	}
}
