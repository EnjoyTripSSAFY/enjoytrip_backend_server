package com.ssafy.enjoytrip_springboot.board.query.service;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import com.ssafy.enjoytrip_springboot.board.query.dto.ReplySingleRequest;

import java.util.List;
import java.util.Optional;

public interface ReplyQueryService {
    List<ReplyDto> getReplies(int articleNo);
}
