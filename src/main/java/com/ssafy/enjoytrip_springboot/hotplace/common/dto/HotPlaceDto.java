package com.ssafy.enjoytrip_springboot.hotplace.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class HotPlaceDto {
    private Long no;
    private String userId;
    private String title;
    private String visitedTime;
    private String registeredTime;
    private int contentType;
    private double rate;
    private String describe;
    private List<String> imageId;
    private double longitude;
    private double latitude;
}
