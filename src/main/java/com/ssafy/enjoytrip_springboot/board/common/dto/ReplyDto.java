package com.ssafy.enjoytrip_springboot.board.common.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ReplyDto {
    private Long no;
    private Long boardNo;
    private String content;
    private String userId;
    private Long parentNo;
    private boolean isDeleted;

    private boolean isBlocked;
    private List<ReplyDto> children = new ArrayList<>();
    private String registerTime;
}
