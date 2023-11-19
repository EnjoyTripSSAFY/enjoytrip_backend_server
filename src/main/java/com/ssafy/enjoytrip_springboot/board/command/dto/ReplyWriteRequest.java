package com.ssafy.enjoytrip_springboot.board.command.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyWriteRequest {

//    @JsonProperty("boardNo")
    private long boardNo;

//    @JsonProperty("userId")
    private String userId;

//    @JsonProperty("content")
    private String content;

//    @JsonProperty("parentNo")
    private Long parentNo;

    public ReplyDto parse(){
        return ReplyDto.builder()
                .boardNo(this.boardNo)
                .userId(this.userId)
                .content(this.content)
                .parentNo(this.parentNo)
                .isBlocked(false)
                .isDeleted(false)
                .registerTime(LocalDateTime.now().toString())
                .build();
    }
}
