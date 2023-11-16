package com.ssafy.enjoytrip_springboot.board.command.mapper;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.board.query.dto.PageOpDto;
import com.ssafy.enjoytrip_springboot.board.query.mapper.BoardQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@MybatisTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Slf4j
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class BoardCommandMapperTest {
    @Autowired
    private BoardQueryMapper queryMapper;

    @Autowired
    private BoardCommandMapper commandMapper;

    @Test
    void contextTest(){
        assertNotNull(queryMapper);
        assertNotNull(commandMapper);
    }

    static private List<BoardDto> getDummies(){
        List<BoardDto> dummies = new ArrayList<>();
        IntStream.range(1, 11).forEach(
                i -> {
                    BoardDto boardDto = new BoardDto();
                    boardDto.setNo(i);
                    boardDto.setContent("테스트 글 작성 중" + i);
                    boardDto.setHit(10 + i);
                    boardDto.setUserId("ssafy");
                    boardDto.setRegisterTime(String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
                    boardDto.setTitle("테스트 케이스 #" + i);
                    dummies.add(boardDto);
                }
        );

        return dummies;
    }


    @Test
    @Transactional
    void writeArticle() throws SQLException {
        List<Long> idx = new ArrayList<>();

        List<BoardDto> dummies = getDummies();
        for (BoardDto dummy: dummies) {
            Long l = commandMapper.writeArticle(dummy);
            idx.add((long) dummy.getNo());
        }

        int targetIndex = new Random().nextInt(10) + 1;
        log.info("들어간 인덱스들 : {}", idx.toString());

        BoardDto actual = queryMapper.getArticle(targetIndex).get();
        BoardDto expected = dummies.get(targetIndex-1);

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void modifyArticle() throws SQLException {
        List<Long> idx = new ArrayList<>();

        List<BoardDto> dummies = getDummies();
        for (BoardDto dummy: dummies) {
            Long l = commandMapper.writeArticle(dummy);
            idx.add((long) dummy.getNo());
        }

        int targetIndex = new Random().nextInt(10) + 1;
        log.info("들어간 인덱스들 : {}", idx.toString());

        BoardDto expected = dummies.get(targetIndex-1);
        expected.setContent("아 몰라 ㅠㅠ");
        expected.setTitle("미워 힝 ㅠㅠ");

        commandMapper.modifyArticle(expected);

        BoardDto actual = queryMapper.getArticle(targetIndex).get();

        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void deleteArticle() throws SQLException {
        List<Long> idx = new ArrayList<>();

        List<BoardDto> dummies = getDummies();
        for (BoardDto dummy: dummies) {
            Long l = commandMapper.writeArticle(dummy);
            idx.add((long) dummy.getNo());
        }

        int target = new Random().nextInt(10) + 1;
        log.info("들어간 인덱스들 : {}", idx.toString());
        log.info("삭제될 개수 : {}", target);


        int expected = dummies.size() - target;

        for(int i=0; i<target; i++){
            int rand = new Random().nextInt(idx.size());
            Long deleteTarget = idx.get(rand);
            commandMapper.deleteArticle(Math.toIntExact(deleteTarget));
            idx.remove(deleteTarget);
        }

        Map<String, Object> param = new PageOpDto().pageMap();
        int actual = queryMapper.listArticle(param).size();


        assertEquals(expected, actual);

    }
}