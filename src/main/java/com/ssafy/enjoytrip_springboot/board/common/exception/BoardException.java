package com.ssafy.enjoytrip_springboot.board.common.exception;

import com.ssafy.enjoytrip_springboot.common.exception.CommonException;

public class BoardException extends CommonException {

    public BoardException(String msg) {
        super(msg);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
