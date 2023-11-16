package com.ssafy.enjoytrip_springboot.userAttraction.command.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(UserAttractionController.class)
class UserAttractionControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void DtoTest() throws Exception {
        //Mock파일생성
        byte[] content = "파일".getBytes(); // 파일의 내용을 바이트 배열로 설정
        String originalFilename = "example.png";
        String contentType = "image/png";
        String name = "firstImage";

        MockMultipartFile file = new MockMultipartFile(name, originalFilename, contentType, content);

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("contentTypeId", "39");
        param.add("title", "놀이방");
        param.add("addr", "서울특별시 강남구 테헤란호 ???");
        param.add("zipcode", "11111");
        param.add("tel" , "02-1234-5678");
        param.add("latitude", "35.2313213");
        param.add("longitude", "27.165165");
        param.add("mlevel", "4");
        param.add("describe", "몰라요");

        MockHttpServletRequestBuilder request = multipart("/hotplce")
                .file(file)
                .params(param);

        MvcResult mvcResult = mockMvc.perform(request)
                .andDo(print())
                .andReturn();

    }
}