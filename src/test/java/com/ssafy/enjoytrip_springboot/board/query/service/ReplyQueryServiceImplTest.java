package com.ssafy.enjoytrip_springboot.board.query.service;

import com.ssafy.enjoytrip_springboot.board.query.dto.ReplyResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;


@SpringBootTest
@Slf4j
class ReplyQueryServiceImplTest {
    @Autowired
    private ReplyQueryService replyQueryService;

    @Test
    public void commentList() throws SQLException {
        List<ReplyResponseDTO> replyList = replyQueryService.getReplies(68);
        log.info(replyList.toString());
    }


}