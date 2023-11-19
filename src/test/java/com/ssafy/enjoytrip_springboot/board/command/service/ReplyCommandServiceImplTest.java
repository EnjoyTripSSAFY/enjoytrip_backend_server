package com.ssafy.enjoytrip_springboot.board.command.service;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.common.exception.BoardException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Transactional
class ReplyCommandServiceImplTest {
    @Autowired
    ReplyCommandService service;


    @Test
    void deleted_fail(){
        ReplyDeleteRequest param =  ReplyDeleteRequest.builder()
                .boardNo(68)
                .no(12)
                .build();

        assertThrows(BoardException.class, () -> {service.deleteReply(param);});
    }

    @Test
    void deleted_true(){
        ReplyDeleteRequest param =  ReplyDeleteRequest.builder()
                .boardNo(68)
                .no(11)
                .build();
    }
}