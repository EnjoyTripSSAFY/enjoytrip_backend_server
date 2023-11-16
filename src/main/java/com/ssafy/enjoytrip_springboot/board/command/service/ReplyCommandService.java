package com.ssafy.enjoytrip_springboot.board.command.service;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;

public interface ReplyCommandService {
    Long writeArticle(ReplyDto replyDto);
    void deleteArticle(ReplyDeleteRequest replyNo);

    int blockArticle(ReplyModifyRequest request);
}
