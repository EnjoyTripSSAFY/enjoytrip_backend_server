package com.ssafy.enjoytrip_springboot.hotplace.query.dto.request;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.hotplace.common.dto.HotPlaceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(implementation = HotPlaceListDto.class)
public class HotPlaceListDto {
    private List<HotPlaceDto> hotPlaces;

    private int currentPage;

    private int totalPageCount;
}
