package com.ssafy.enjoytrip_springboot.trip.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class RegionResponse {
    private int code;
    private String name;

}
