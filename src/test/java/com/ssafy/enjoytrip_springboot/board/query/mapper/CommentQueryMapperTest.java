package com.ssafy.enjoytrip_springboot.board.query.mapper;

import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;


@SpringBootTest
@Slf4j
class CommentQueryMapperTest {
    @Autowired
    private ReplyQueryMapper commentQueryMapper;

    @Test
    public void commentList() throws SQLException {
        List<ReplyDto> replyList = commentQueryMapper.getReplyList(68);
        replyList.forEach(r -> log.info(r.toString()));
    }
}