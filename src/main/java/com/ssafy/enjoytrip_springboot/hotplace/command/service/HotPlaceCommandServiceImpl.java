package com.ssafy.enjoytrip_springboot.hotplace.command.service;

import com.ssafy.enjoytrip_springboot.hotplace.command.mapper.HotPlaceCommandMapper;
import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceCommandServiceImpl implements HotPlaceCommandService {
    private final HotPlaceCommandMapper commandMapper;

    @Override
    public Long registerHotPlace(HotPlaceDto request) {
        commandMapper.registHotPlace(request);
        commandMapper.registHotPlaceImageList(request);
        return request.getNo();
    }

    @Override
    public void deleteHotPlace(Long id) {
        commandMapper.deleteHotplaceImageList(HotPlaceDto.builder().no(id).build());
        commandMapper.deleteHotPlace(id);
    }

    @Override
    public Long modifyHotPlace(HotPlaceDto request) {
        commandMapper.deleteHotplaceImageList(request);
        commandMapper.updateHotplace(request);
        commandMapper.registHotPlaceImageList(request);

        return request.getNo();
    }
}
