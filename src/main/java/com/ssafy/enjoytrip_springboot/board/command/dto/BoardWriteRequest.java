package com.ssafy.enjoytrip_springboot.board.command.dto;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardWriteRequest {
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
                .userId(this.getUserId())
                .title(this.getTitle())
                .content(this.getContent())
                .registerTime(LocalDateTime.now().toString())
                .hit(0)
                .build();
    }
}
