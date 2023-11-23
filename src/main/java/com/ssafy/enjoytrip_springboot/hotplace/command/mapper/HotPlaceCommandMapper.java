package com.ssafy.enjoytrip_springboot.hotplace.command.mapper;

import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotPlaceCommandMapper {
    Long registHotPlace(HotPlaceDto hotplace);
    Long registHotPlaceImageList(HotPlaceDto hotplace);

    Long updateHotplace(HotPlaceDto hotplace);

    void deleteHotplaceImageList(HotPlaceDto hotplaceDto);

    void deleteHotPlace(long no);
}
