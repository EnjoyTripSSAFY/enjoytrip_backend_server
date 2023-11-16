package com.ssafy.enjoytrip_springboot.board.command.dto;

import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyWriteRequest {
    private int boardId;
    private String userId;
    private String content;
    private Integer parentNo;

    public ReplyDto parse(){
        return ReplyDto.builder()
                .boardId(this.boardId)
                .userId(this.userId)
                .content(this.content)
                .parentNo(this.parentNo)
                .isBlocked(false)
                .isDeleted(false)
                .registerTime(LocalDateTime.now().toString())
                .build();
    }
}
