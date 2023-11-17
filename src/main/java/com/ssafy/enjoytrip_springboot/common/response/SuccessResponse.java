package com.ssafy.enjoytrip_springboot.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class SuccessResponse {

    HttpStatus httpStatus;
    String msg;
    Object result;

    public SuccessResponse(HttpStatus httpStatus, String msg){
        this.httpStatus = httpStatus;
        this.msg = msg;
    }
}
