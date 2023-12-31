package com.ssafy.enjoytrip_springboot.common.s3.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UploadFile {
    private int no;
    private String originalFile;
    private String saveFile;
}
