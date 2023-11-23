package com.ssafy.enjoytrip_springboot.hotplace.query.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

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
    private String currentPage = "1";
    @ApiModelProperty(value = "페이지 번호", required = true)
    private int size = 10;


    public int getStart(String pgno){
        return getCurrentPage() * size - size;
    }

    public int getCurrentPage() {
        return Integer.parseInt(this.currentPage);
    }

    private boolean validateValue(String obj){
        return (obj == null || obj.isEmpty());
    }

    private String defaultValue(String target, String defaultValue){
        return validateValue(target) ? defaultValue : target;
    }


    public Map<String, Object> pageMap(){
        String pgno = defaultValue(this.currentPage, "1");
        int start = getStart(pgno);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", defaultValue(this.key, ""));
        map.put("word", defaultValue(this.word, ""));
        map.put("start", start);
        map.put("listsize", size);

        return map;
    }
}
