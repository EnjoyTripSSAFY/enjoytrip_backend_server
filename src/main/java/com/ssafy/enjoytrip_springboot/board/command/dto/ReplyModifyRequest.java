package com.ssafy.enjoytrip_springboot.board.command.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.enjoytrip_springboot.board.query.dto.ReplySingleRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReplyModifyRequest {

//    @JsonProperty("boardNo")
    private int boardNo;

//    @JsonProperty("no")
    private int no;

//    @JsonProperty("content")
    private String content;
}
