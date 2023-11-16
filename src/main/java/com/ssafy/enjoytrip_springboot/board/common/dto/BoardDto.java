package com.ssafy.enjoytrip_springboot.board.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class BoardDto {

	@ApiModelProperty(value = "pk", required = false)
	private int no;
	@ApiModelProperty(value = "유저 아이디", required = true)
	private String userId;
	@ApiModelProperty(value = "제목", required = true)
	private String title;

	@ApiModelProperty(value = "내용", required = true)
	private String content;
	@ApiModelProperty(value = "조회수", required = false, hidden = true)
	private int hit;
	@ApiModelProperty(value = "등록일자", required = false, hidden = true)
	private String registerTime;
	@ApiModelProperty(value = "삭제여부")
	private boolean isDeleted;
	@ApiModelProperty(value = "차단여부")
	private boolean isBlocked;
	@ApiModelProperty(value = "댓글들", required = false, hidden = true)
	private List<ReplyDto> replies;
}
