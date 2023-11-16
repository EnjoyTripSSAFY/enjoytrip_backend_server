package com.ssafy.enjoytrip_springboot.userAttraction.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class UserAttractionInfoRequest {
    private int contentTypeId;
    private String title;
    private String addr;
    private String zipcode;
    private String tel;

    @JsonIgnore
    private MultipartFile firstImage;
    private double latitude;
    private double longitude;
    private String mlevel;
    private String describe;
}

