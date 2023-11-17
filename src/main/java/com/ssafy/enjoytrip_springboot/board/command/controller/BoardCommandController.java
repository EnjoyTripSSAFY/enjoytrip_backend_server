package com.ssafy.enjoytrip_springboot.board.command.controller;

import com.ssafy.enjoytrip_springboot.board.command.dto.BoardModifyRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.BoardWriteRequest;
import com.ssafy.enjoytrip_springboot.board.command.service.BoardCommandService;
import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Api(tags = { "1-2. BoardCommandController" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardCommandController {
    private final BoardCommandService commandService;

    @PostMapping("/")
    public ResponseEntity<?> write(@RequestBody BoardWriteRequest request) throws Exception {
        Long id = commandService.writeArticle(request.parse());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", id);

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(resultMap)
                .msg("정상적으로 등록 되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws Exception {
        commandService.deleteArticle(id);


        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .msg("정상적으로 삭제되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/")
    public ResponseEntity<?> modify(@RequestBody BoardModifyRequest request) throws Exception {
        long id = commandService.modifyArticle(request.parse());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", id);

        SuccessResponse response = SuccessResponse.builder()
                .httpStatus(HttpStatus.OK)
                .result(resultMap)
                .msg("정상적으로 수정 되었습니다")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/block/{no}")
    public ResponseEntity<?> block(@PathVariable int no) throws Exception {
        int id = commandService.blockArticle(no);

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
