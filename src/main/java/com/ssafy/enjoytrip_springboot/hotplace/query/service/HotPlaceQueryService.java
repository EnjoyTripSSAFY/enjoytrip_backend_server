package com.ssafy.enjoytrip_springboot.hotplace.query.service;

import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;

import java.util.List;
import java.util.Map;

public interface HotPlaceQueryService {
    List<HotPlaceDto> listHotPlaces(Map<String, Object> pageMap);

    HotPlaceDto getHotPlace(long hotPlaceNo);

    int getTotalHotPlaceCount(Map<String, Object> pageMap);


}
