package com.ssafy.enjoytrip_springboot.hotplace.query.service;

import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {
    @Override
    public List<HotPlaceDto> listHotPlaces(Map<String, Object> pageMap) {
        return null;
    }

    @Override
    public HotPlaceDto getHotPlace(long hotPlaceNo) {
        return null;
    }

    @Override
    public int getTotalHotPlaceCount(Map<String, Object> pageMap) {
        return 0;
    }
}
