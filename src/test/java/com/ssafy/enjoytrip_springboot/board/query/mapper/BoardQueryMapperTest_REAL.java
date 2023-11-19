//package com.ssafy.enjoytrip_springboot.board.query.mapper;
//
//import com.ssafy.enjoytrip_springboot.board.command.mapper.BoardCommandMapper;
//import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
//import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
//import com.ssafy.enjoytrip_springboot.common.util.SizeConstant;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.test.annotation.DirtiesContext;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
//
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Slf4j
//@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
//class BoardQueryMapperTest_REAL {
//    @Autowired
//    private BoardQueryMapper boardQueryMapper;
//
//    @Autowired
//    private BoardCommandMapper boardCommandMapper;
//
//
//    @Test
//    void context(){
//        assertNotNull(boardQueryMapper);
//    }
//
//    static private List<BoardDto> getDummies(){
//        List<BoardDto> dummies = new ArrayList<>();
//        IntStream.range(1, 23).forEach(
//                i -> {
//                    BoardDto boardDto = new BoardDto();
//                    boardDto.setNo(i);
//                    boardDto.setContent("테스트 글 작성 중" + i);
//                    boardDto.setHit(10 + i);
//                    boardDto.setUserId("ssafy");
//                    boardDto.setRegisterTime(LocalDateTime.now().toString());
//                    boardDto.setTitle("테스트 케이스 #" + i);
//                    dummies.add(boardDto);
//                }
//        );
//
//        return dummies;
//    }
//
//    void testSetup() throws SQLException {
//        List<BoardDto> dummies = getDummies();
//        for (BoardDto dummy : dummies) {
//            System.out.println(dummy);
//            boardCommandMapper.writeArticle(dummy);
//        }
//    }
//
////    @Test
////    void listArticle() throws SQLException {
////        testSetup();
////
////        Map<String, Object> condition = new HashMap<>();
////        int pgno = 1;
////        int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
////        condition.put("start", start);
////        condition.put("listsize", SizeConstant.LIST_SIZE);
////        List<BoardDto> list = boardQueryMapper.listArticle(condition);
////
////        list.forEach(System.out::println);
////    }
//
//    @Test
//    void getTotalArticleCount() throws SQLException {
//        testSetup();
//
//        Map<String, Object> condition = new HashMap<>();
//        int pgno = 1;
//        int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//        condition.put("start", start);
//        condition.put("listsize", SizeConstant.LIST_SIZE);
//        int totalArticleCount = boardQueryMapper.getTotalArticleCount(condition);
//
//        System.out.println(totalArticleCount);
//
//    }
//
//    @Test
//    void getArticle() throws SQLException {
//        List<Long> idx = new ArrayList<>();
//
//        List<BoardDto> dummies = getDummies();
//        for (BoardDto dummy: dummies) {
//            Long l = boardCommandMapper.writeArticle(dummy);
//            idx.add((long) dummy.getNo());
//        }
//
//        log.info("들어간 인덱스들 : {}", idx.toString());
//
//        int target = new Random().nextInt(dummies.size()) + 1;
//        log.info(target + "");
//
//        BoardDto article = boardQueryMapper.getArticle(target).get();
//        assertNotNull(article);
//        assertTrue(article.getContent().contains(target+""));
//    }
//
//
//    @Test
//    void get_real_article() throws SQLException {
//        BoardDto article = boardQueryMapper.getArticle(68).get();
//
//        System.out.println("article.getNo() = " + article.getNo());
//        System.out.println("article.getContent() = " + article.getContent());
//        System.out.println("article.getTitle() = " + article.getTitle());
//        System.out.println("article.getUserId() = " + article.getUserId());
//
//        List<ReplyDto> replies = article.getReplies();
//        replies.forEach(System.out::println);
//    }
//}