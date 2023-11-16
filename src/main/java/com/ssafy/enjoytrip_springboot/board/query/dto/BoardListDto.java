package com.ssafy.enjoytrip_springboot.board.query.dto;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(implementation = BoardListDto.class)
public class BoardListDto {
    private List<BoardDto> boards;

    private int currentPage;

    private int totalPageCount;
}
