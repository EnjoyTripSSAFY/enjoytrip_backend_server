package com.ssafy.enjoytrip_springboot.board.query.controller;

import com.ssafy.enjoytrip_springboot.board.query.dto.ReplyResponseDTO;
import com.ssafy.enjoytrip_springboot.board.query.service.ReplyQueryService;
import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


@Api(tags = { "1-3. ReplyQueryController" })
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyQueryController {

    private final ReplyQueryService replyQueryService;


    @GetMapping("/{article_no}")
    public ResponseEntity<?> getReplyList(@PathVariable int article_no) throws SQLException {
        List<ReplyResponseDTO> result = replyQueryService.getReplies(article_no);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "article 댓글 리스트 조회 성공", result));

    }
}
