package com.ssafy.enjoytrip_springboot.hotplace.command.dto.request;

import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceRegistRequest {
    private String userId;
    private String title;
    private String visitedTime;
    private int contentType;
    private double rate;
    private String describe;
    private List<String> imageId;
    private double longitude;
    private double latitude;

    public HotPlaceDto parse() {
        return null;
    }
}
