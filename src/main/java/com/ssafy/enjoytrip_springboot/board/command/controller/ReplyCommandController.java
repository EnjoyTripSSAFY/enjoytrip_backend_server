package com.ssafy.enjoytrip_springboot.board.command.controller;


import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyWriteRequest;
import com.ssafy.enjoytrip_springboot.board.command.service.ReplyCommandService;
import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = { "1-4. ReplyCommandController" })
@RestController
@RequestMapping("reply")
@RequiredArgsConstructor
public class ReplyCommandController {
    private final ReplyCommandService replyCommandService;

    @PostMapping("/{article}")
    public ResponseEntity<?> registReply(ReplyWriteRequest request) {
        Long id = replyCommandService.writeArticle(request.parse());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", id);

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(resultMap)
                .msg("정상적으로 등록 되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(ReplyDeleteRequest request) throws Exception {
        replyCommandService.deleteArticle(request);
        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .msg("정상적으로 삭제되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/block/")
    public ResponseEntity<?> block(ReplyModifyRequest request) throws Exception {
        int id = replyCommandService.blockArticle(request);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", id);

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(resultMap)
                .msg("정상적으로 블락 되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }
}
