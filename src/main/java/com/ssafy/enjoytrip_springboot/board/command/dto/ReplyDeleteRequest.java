package com.ssafy.enjoytrip_springboot.board.command.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.enjoytrip_springboot.board.query.dto.ReplySingleRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReplyDeleteRequest {

//    @JsonProperty("boardNo")
    private int boardNo;

//    @JsonProperty("no")
    private int no;

    public ReplySingleRequest parse() {
       return ReplySingleRequest.builder()
               .boardNo(this.boardNo)
               .no(this.no)
               .build();
    }
}
