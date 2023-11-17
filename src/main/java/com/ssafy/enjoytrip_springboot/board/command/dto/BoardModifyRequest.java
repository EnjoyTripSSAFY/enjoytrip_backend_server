package com.ssafy.enjoytrip_springboot.board.command.dto;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@ApiModel
@Data
@Builder
public class BoardModifyRequest {
    @ApiModelProperty(value = "pk", required = false)
    private int no;
    @ApiModelProperty(value = "유저 아이디", required = true)
    private String userId;
    @ApiModelProperty(value = "제목", required = true)
    private String title;
    @ApiModelProperty(value = "내용", required = true)
    private String content;
    @ApiModelProperty(value = "삭제여부")
    private boolean isDeleted;
    @ApiModelProperty(value = "차단여부")
    private boolean isBlocked;

    public BoardDto parse(){
        return BoardDto.builder()
                .no(this.getNo())
                .title(this.getTitle())
                .content(this.getContent())
                .build();
    }
}
