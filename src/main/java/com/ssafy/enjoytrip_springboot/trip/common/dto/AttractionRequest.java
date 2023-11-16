package com.ssafy.enjoytrip_springboot.trip.common.dto;

import com.ssafy.enjoytrip_springboot.common.util.SizeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import springfox.documentation.annotations.ApiIgnore;

@Data
@ApiModel
public class AttractionRequest {
    private int areaCode;
    private int sigunguCode;
    private int contentTypeId;
    private int pageNo;
    private String word;

    @ApiModelProperty(hidden = true)
    private int numOfRows = SizeConstant.LIST_SIZE;

    @ApiModelProperty(hidden = true)
    private int start;

    @Builder
    public AttractionRequest(int areaCode, int sigunguCode, int contentTypeId,
                             int pageNo, String word) {
        this.areaCode = areaCode;
        this.sigunguCode = sigunguCode;
        this.contentTypeId = contentTypeId;
        this.pageNo = pageNo;
        this.word = word;
    }

    public int getStart() {
        if (pageNo <= 0) pageNo = 1;
        return this.start = (pageNo * numOfRows) - numOfRows;
    }

}
