package com.ssafy.enjoytrip_springboot.board.query.dto;

import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyResponseDTO {

    private Long id;
    private String content;
    private String userId;
    private String registerTime;
    private boolean isDeleted;
    private boolean isBlocked;
    private List<ReplyResponseDTO> children = new ArrayList<>();

    @Builder
    public ReplyResponseDTO(Long id, String content, String userId, String registerTime,
                            boolean isDeleted, boolean isBlocked) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.registerTime = registerTime;
        this.isBlocked = isBlocked;
        this.isDeleted = isDeleted;
    }

    public static ReplyResponseDTO convertCommentToDto(ReplyDto comment) {
        return comment.isDeleted() ?
                new ReplyResponseDTO(comment.getNo(), "삭제된 댓글입니다.", null, null, true, comment.isBlocked()) :
                new ReplyResponseDTO(comment.getNo(), comment.getContent(), comment.getUserId(), comment.getRegisterTime(), false, comment.isBlocked());
    }
}
