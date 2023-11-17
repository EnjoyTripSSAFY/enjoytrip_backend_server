package com.ssafy.enjoytrip_springboot.board.query.controller;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.board.query.dto.BoardListDto;
import com.ssafy.enjoytrip_springboot.board.query.dto.PageOpDto;
import com.ssafy.enjoytrip_springboot.board.query.service.BoardQueryService;
import com.ssafy.enjoytrip_springboot.common.response.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "1-1. BoardQueryController" })
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardQueryController {

    private final BoardQueryService queryService;

    @Operation(
            summary = "Create a new customer",
            description = "Create a new customer with the given data",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(schema = @Schema(implementation = BoardListDto.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> listArticle(PageOpDto pageOpDto) {
        HttpHeaders headers = new HttpHeaders();

        Map<String, Object> resultMap = new HashMap<>();

        Map<String, Object> pageMap = pageOpDto.pageMap();
        List<BoardDto> boardDtos = queryService.listArticle(pageMap);
        int totalCount = queryService.getTotalArticleCount(pageMap);

        BoardListDto result = BoardListDto.builder()
                .boards(boardDtos)
                .currentPage(pageOpDto.getCurrentPage())
                .totalPageCount(totalCount)
                .build();

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "article list 조회 성공", result));
    }

    @GetMapping("/{articleNo}")
    public ResponseEntity<?> getArticle(@PathVariable("articleNo") int articleNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        BoardDto result = queryService.getArticle(articleNo);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, "article 조회 성공", result));

    }
}
