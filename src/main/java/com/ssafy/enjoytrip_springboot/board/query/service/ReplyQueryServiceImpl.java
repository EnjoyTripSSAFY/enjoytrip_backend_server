package com.ssafy.enjoytrip_springboot.board.query.service;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import com.ssafy.enjoytrip_springboot.board.common.exception.BoardException;
import com.ssafy.enjoytrip_springboot.board.query.dto.ReplySingleRequest;
import com.ssafy.enjoytrip_springboot.board.query.mapper.ReplyQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyQueryMapper queryMapper;

    @Override
    public List<ReplyDto> getReplies(int articleNo) {
        try {
            return queryMapper.getReplyList(articleNo);

        } catch (SQLException e) {
            throw new BoardException("댓글을 받아 올 수 없습니다");
        }
    }

}
