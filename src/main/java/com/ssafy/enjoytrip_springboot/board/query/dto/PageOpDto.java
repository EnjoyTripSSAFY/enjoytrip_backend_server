package com.ssafy.enjoytrip_springboot.board.query.dto;

import com.ssafy.enjoytrip_springboot.common.util.SizeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class PageOpDto {

    @Hidden
    @ApiModelProperty(value = "검색 카테고리", required = false)
    private String key;
    @ApiModelProperty(value = "검색 내용", required = false)
    private String word;
    @ApiModelProperty(value = "페이지 번호", required = true)
    private String pgno;


    public int getStart(String pgno){
        return getPgno() * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
    }

    public int getPgno() {
        return Integer.parseInt(this.pgno);
    }

    private boolean validateValue(String obj){
        return (obj == null || obj.isEmpty());
    }

    private String defaultValue(String target, String defaultValue){
        return validateValue(target) ? defaultValue : target;
    }


    public Map<String, Object> pageMap(){
        String pgno = defaultValue(this.pgno, "1");
        int start = getStart(pgno);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", defaultValue(this.key, ""));
        map.put("word", defaultValue(this.word, ""));
        map.put("start", start);
        map.put("listsize", SizeConstant.LIST_SIZE);

        return map;
    }
}
