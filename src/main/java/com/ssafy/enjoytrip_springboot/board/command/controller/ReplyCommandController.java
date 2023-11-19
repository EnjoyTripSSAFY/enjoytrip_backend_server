package com.ssafy.enjoytrip_springboot.board.command.controller;


import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyWriteRequest;
import com.ssafy.enjoytrip_springboot.board.command.service.ReplyCommandService;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = { "1-4. ReplyCommandController" })
@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Slf4j
public class ReplyCommandController {
    private final ReplyCommandService replyCommandService;

    @PostMapping("/")
    public ResponseEntity<?> registReply(@RequestBody ReplyWriteRequest request) {

        ReplyDto reply = request.parse();
        log.info(request.toString());
        log.info(reply.toString());
        Long id = replyCommandService.writeReply(reply);

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
    public ResponseEntity<?> delete(@RequestBody ReplyDeleteRequest request) throws Exception {
        replyCommandService.deleteReply(request);
        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .msg("정상적으로 삭제되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/block/")
    public ResponseEntity<?> block(@RequestBody ReplyModifyRequest request) throws Exception {
        int id = replyCommandService.modifyReply(request);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", id);

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(resultMap)
                .msg("정상적으로 블락 되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody ReplyModifyRequest request) throws Exception {
        int id = replyCommandService.modifyReply(request);

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(id)
                .msg("정상적으로 수정 되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }
}
