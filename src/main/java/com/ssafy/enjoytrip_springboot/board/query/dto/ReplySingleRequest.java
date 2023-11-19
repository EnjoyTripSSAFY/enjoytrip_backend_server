package com.ssafy.enjoytrip_springboot.board.query.dto;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReplySingleRequest {
    private int boardNo;
    private int no;

    public ReplyDeleteRequest parse() {
        return ReplyDeleteRequest.builder()
                .boardNo(this.boardNo)
                .no(no)
                .build();
    }
}
