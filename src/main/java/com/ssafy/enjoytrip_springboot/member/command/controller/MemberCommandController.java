package com.ssafy.enjoytrip_springboot.member.command.controller;

import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.ChangeRoleDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.JoinMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.LoginRequestDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.UpdateMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.response.LoginResponseDto;
import com.ssafy.enjoytrip_springboot.member.command.service.MemberCommandService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Api(tags = { "2-2. MemberCommandController" })
@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberCommandController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody JoinMemberDto joinMemberDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int result = memberCommandService.joinMember(joinMemberDto);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "join 성공", result));
    }

    @PutMapping("/changeRole")
    public ResponseEntity<?> changeRole(@RequestBody ChangeRoleDto changeRoleDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        int result = memberCommandService.changeRole(changeRoleDto);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "changeRole 성공", result));
    }

    @PutMapping
    public ResponseEntity<?> updateMember(@RequestBody UpdateMemberDto updateMemberDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> resultMap = new HashMap<>();
        int result = memberCommandService.updateMember(updateMemberDto);
        resultMap.put("result", result);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "update 성공", resultMap));
    }

    @PutMapping("/withdrawal/{userId}")
    public ResponseEntity<?> deleteMember(@PathVariable String userId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> resultMap = new HashMap<>();
        int result = memberCommandService.deleteMember(userId);
        resultMap.put("result", result);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "delete 성공", resultMap));
    }

}
