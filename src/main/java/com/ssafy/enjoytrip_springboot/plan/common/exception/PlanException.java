package com.ssafy.enjoytrip_springboot.plan.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlanException extends RuntimeException {

    public PlanException(String msg) {
        super(msg);
    }
}
