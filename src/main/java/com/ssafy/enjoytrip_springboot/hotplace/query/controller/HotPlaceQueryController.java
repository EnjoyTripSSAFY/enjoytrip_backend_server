package com.ssafy.enjoytrip_springboot.hotplace.query.controller;

import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;
import com.ssafy.enjoytrip_springboot.hotplace.query.dto.request.HotPlaceListDto;
import com.ssafy.enjoytrip_springboot.hotplace.query.dto.request.PageOpDto;
import com.ssafy.enjoytrip_springboot.hotplace.query.service.HotPlaceQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotplace")
public class HotPlaceQueryController {

    private final HotPlaceQueryService queryService;

    @GetMapping
    public ResponseEntity<?> listHotPlaces(PageOpDto pageOpDto) {
        Map<String, Object> resultMap = new HashMap<>();

        Map<String, Object> pageMap = pageOpDto.pageMap();
        List<HotPlaceDto> hotPlaceDtos = queryService.listHotPlaces(pageMap);
        int totalCount = queryService.getTotalHotPlaceCount(pageMap);

        HotPlaceListDto result = HotPlaceListDto.builder()
                .hotPlaces(hotPlaceDtos)
                .currentPage(pageOpDto.getCurrentPage())
                .totalPageCount(totalCount)
                .build();

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "hotplace list 조회 성공", result));
    }

    @GetMapping("/{hotPlaceNo}")
    public ResponseEntity<?> getHotPlace(@PathVariable("hotPlaceNo") long hotPlaceNo) {
        HotPlaceDto result = queryService.getHotPlace(hotPlaceNo);
        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "hotplace 조회 성공", result));
    }
}

