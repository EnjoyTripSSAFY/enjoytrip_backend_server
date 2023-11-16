package com.ssafy.enjoytrip_springboot.board.common.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ReplyDto {
    private int no;  // 댓글의 고유 식별자
    private int boardId;  // 댓글이 속한 게시물의 ID
    private int level; // 깊이 레벨
    private String userId;  // 작성자명
    private boolean isDeleted; // 삭제 여부
    private boolean isBlocked; // 제제 여부
    private Integer parentNo;
    private String content;  // 댓글 내용
    private String registerTime;  // 댓글 등록 시간
}
