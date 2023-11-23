package com.ssafy.enjoytrip_springboot.hotplace.query.mapper;

import com.ssafy.enjoytrip_springboot.hotplace.query.dto.request.AntFileUpload;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotPlaceQueryMapper {

    List<AntFileUpload> getFileData(Long hotplaceNo);

}
