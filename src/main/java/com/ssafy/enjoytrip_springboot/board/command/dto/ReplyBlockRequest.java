package com.ssafy.enjoytrip_springboot.board.command.dto;

import com.ssafy.enjoytrip_springboot.board.query.dto.ReplySingleRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReplyBlockRequest {
    private int boardNo;
    private int no;

}
