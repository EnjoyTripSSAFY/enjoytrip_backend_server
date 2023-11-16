package com.ssafy.enjoytrip_springboot.board.query.mapper;

import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ReplyQueryMapperTest {

    @Autowired
    private ReplyQueryMapper replyQueryMapper;

    @Test
    public void findArticleReplies() throws SQLException {
        List<ReplyDto> replyList = replyQueryMapper.getReplyList(68);
        log.info(replyList.size() + "");
        assertNotNull(replyList);
        assertFalse(replyList.isEmpty());
    }
}