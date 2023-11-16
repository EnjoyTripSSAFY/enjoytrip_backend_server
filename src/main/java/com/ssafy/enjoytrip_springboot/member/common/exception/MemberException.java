package com.ssafy.enjoytrip_springboot.member.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberException extends RuntimeException{

    public MemberException(String msg) {
        super(msg);
    }
}
