package com.ssafy.enjoytrip_springboot.board.command.mapper;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyBlockRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyWriteRequest;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyCommandMapper {
    Long writeReply(ReplyDto request);
    void deleteReply(ReplyDeleteRequest request);
    Long modifyReply(ReplyModifyRequest request);

    void blockReply(ReplyBlockRequest request);
}
