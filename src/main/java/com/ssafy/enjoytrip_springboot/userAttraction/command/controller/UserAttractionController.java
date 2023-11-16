package com.ssafy.enjoytrip_springboot.userAttraction.command.controller;

import com.ssafy.enjoytrip_springboot.userAttraction.common.UserAttractionInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotplce")
@Slf4j
public class UserAttractionController {

    @PostMapping("")
    public ResponseEntity<?> createHotPlace(UserAttractionInfoRequest request){
        log.info(request.toString());
        log.info(request.getFirstImage() != null ? request.getFirstImage().getOriginalFilename() : "");
        return ResponseEntity.ok().build();
    }
}
