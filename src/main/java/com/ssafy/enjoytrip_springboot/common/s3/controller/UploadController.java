package com.ssafy.enjoytrip_springboot.common.s3.controller;


import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.common.s3.Dto.UploadFile;
import com.ssafy.enjoytrip_springboot.common.s3.service.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
    private final FileUpload uploadService;

    @PostMapping("/single")
    public ResponseEntity<?> upload(MultipartFile file) throws IOException {
        UploadFile uploadFile = uploadService.uploadFile(file);

        SuccessResponse response = SuccessResponse.builder()
                .result(uploadFile)
                .build();

        return ResponseEntity.ok(response);
    }

}


