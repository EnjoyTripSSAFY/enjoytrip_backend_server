package com.ssafy.enjoytrip_springboot.board.command.mapper;

import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyDeleteRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyModifyRequest;
import com.ssafy.enjoytrip_springboot.board.command.dto.ReplyWriteRequest;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReplyCommandMapperTest {

    @Autowired
    ReplyCommandMapper commandMapper;

    @Test
    void writeReply() {
        ReplyWriteRequest request = new ReplyWriteRequest(68, "ssafi", "안녕못해요", 1L);
        ReplyDto replyDto = request.parse();
        commandMapper.writeReply(replyDto);
        Long no = replyDto.getNo();
        assertNotNull(no);
    }

    @Test
    void deleteReply() {
        ReplyWriteRequest request = new ReplyWriteRequest(68, "ssafi", "안녕못해요22", 3L);
        ReplyDto replyDto = request.parse();
        commandMapper.writeReply(replyDto);
        Long no = replyDto.getNo();

        ReplyDeleteRequest deleteRequest = new ReplyDeleteRequest(68, Math.toIntExact(no));
        commandMapper.deleteReply(deleteRequest);
    }

    @Test
    void modifyReply() {
        ReplyWriteRequest request = new ReplyWriteRequest(68, "ssafi", "안녕못해요22", 3L);
        ReplyDto replyDto = request.parse();
        commandMapper.writeReply(replyDto);
        Long no = replyDto.getNo();

        ReplyModifyRequest modifyRequest = new ReplyModifyRequest(68, Math.toIntExact(no), "안녕하세요22");
        Long modify = commandMapper.modifyReply(modifyRequest);

        assertNotEquals(request.getContent(), modifyRequest.getContent());
    }

    @Test
    void blockReply() {
    }
}