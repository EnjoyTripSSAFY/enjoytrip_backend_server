package com.ssafy.enjoytrip_springboot.hotplace.command.service;

import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootTest
class HotPlaceCommandServiceTest {

    @Autowired
    private HotPlaceCommandService hotPlaceCommandService;

    @Test
    void registerHotPlace() {
        HotPlaceDto mock = HotPlaceDto.builder()
                .contentType(39)
                .describe("야호~")
                .latitude(33.11111)
                .longitude(122.123123)
                .imageId(Arrays.asList("1", "2", "3", "4"))
                .visitedTime(LocalDateTime.now().minusDays(3).toString())
                .rate(4.5)
                .title("삼성전자")
                .registeredTime(LocalDateTime.now().toString())
                .userId("ssafy1")
                .build();

        hotPlaceCommandService.registerHotPlace(mock);
    }

    @Test
    void deleteHotPlace() {
        HotPlaceDto mock = HotPlaceDto.builder()
                .contentType(39)
                .describe("야호~")
                .latitude(33.11111)
                .longitude(122.123123)
                .imageId(Arrays.asList("4", "5", "6", "7"))
                .visitedTime(LocalDateTime.now().minusDays(3).toString())
                .rate(4.5)
                .title("대우전자")
                .registeredTime(LocalDateTime.now().toString())
                .userId("ssafy1")
                .build();

        hotPlaceCommandService.registerHotPlace(mock);
        hotPlaceCommandService.deleteHotPlace(mock.getNo());
    }

    @Test
    void modifyHotPlace() {
        HotPlaceDto mock = HotPlaceDto.builder()
                .contentType(39)
                .describe("야호~")
                .latitude(33.11111)
                .longitude(122.123123)
                .imageId(Arrays.asList("4", "5", "6", "7"))
                .visitedTime(LocalDateTime.now().minusDays(3).toString())
                .rate(4.5)
                .title("대우전자")
                .registeredTime(LocalDateTime.now().toString())
                .userId("ssafy1")
                .build();

        hotPlaceCommandService.registerHotPlace(mock);


        HotPlaceDto request = HotPlaceDto.builder()
                .no(mock.getNo())
                .contentType(39)
                .describe("야호~")
                .latitude(33.11111)
                .longitude(122.123123)
                .imageId(Arrays.asList("4", "5", "6"))
                .visitedTime(LocalDateTime.now().minusDays(3).toString())
                .rate(4.5)
                .title("대우전자")
                .registeredTime(LocalDateTime.now().toString())
                .userId("ssafy1")
                .build();

        hotPlaceCommandService.modifyHotPlace(request);

    }
}