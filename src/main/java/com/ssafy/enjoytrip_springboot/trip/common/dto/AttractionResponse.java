package com.ssafy.enjoytrip_springboot.trip.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class AttractionResponse {
    private String addr1;
    private String addr2;
    private int areacode;
    private int contentid;
    private int contenttypeid;
    private String firstimage;
    private String firstimage2;
    private double mapx;
    private double mapy;
    private String mlevel;
    private int sigungucode;
    private String tel;
    private String title;
    private String zipcode;

}
