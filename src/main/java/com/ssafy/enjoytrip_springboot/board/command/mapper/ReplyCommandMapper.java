package com.ssafy.enjoytrip_springboot.board.command.mapper;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyCommandMapper {
    Long writeReply(ReplyDto replyDto);
    void deleteReply(ReplyDeleteRequest request);

    void blockReply(ReplyModifyRequest request);
}
