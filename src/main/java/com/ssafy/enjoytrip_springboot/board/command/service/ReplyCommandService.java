package com.ssafy.enjoytrip_springboot.board.command.service;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyBlockRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;

public interface ReplyCommandService {
    Long writeReply(ReplyDto replyDto);
    void deleteReply(ReplyDeleteRequest replyNo);

    int modifyReply(ReplyModifyRequest request);
    int blockReply(ReplyBlockRequest request);
}
