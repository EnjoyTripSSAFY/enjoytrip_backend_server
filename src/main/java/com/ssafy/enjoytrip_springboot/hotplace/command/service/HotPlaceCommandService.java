package com.ssafy.enjoytrip_springboot.hotplace.command.service;

import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;

public interface HotPlaceCommandService {
    Long registerHotPlace(HotPlaceDto request);

    void deleteHotPlace(Long id);

    Long modifyHotPlace(HotPlaceDto request);
}
