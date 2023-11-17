package com.ssafy.enjoytrip_springboot.common.s3.service;

import com.ssafy.enjoytrip_springboot.common.s3.Dto.UploadFile;
import com.ssafy.enjoytrip_springboot.common.s3.Dto.UploadFileSearch;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUpload {
    UploadFile uploadFile(MultipartFile file) throws IOException;

    List<UploadFile> FindFileData(UploadFileSearch search);
}
