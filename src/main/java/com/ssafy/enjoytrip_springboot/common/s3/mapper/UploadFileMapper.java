package com.ssafy.enjoytrip_springboot.common.s3.mapper;


import com.ssafy.enjoytrip_springboot.common.s3.Dto.UploadFile;
import com.ssafy.enjoytrip_springboot.common.s3.Dto.UploadFileSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadFileMapper {
    Long insertFile(UploadFile file);
    List<UploadFile> findFileData(UploadFileSearch search);
}
