package com.ssafy.enjoytrip_springboot.member.query.controller;

import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.member.query.dto.response.GetMemberDto;
import com.ssafy.enjoytrip_springboot.member.query.service.MemberQueryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Api(tags = { "2-1. MemberQueryController" })
@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberQueryController {

    private final MemberQueryService queryService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getMember(@PathVariable("userId") String userId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> resultMap = new HashMap<>();
        GetMemberDto find = queryService.getMember(userId);
        resultMap.put("result", find);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "유저 조회 성공", resultMap));
    }

}
