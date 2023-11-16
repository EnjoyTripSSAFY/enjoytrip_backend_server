package com.ssafy.enjoytrip_springboot.board.query.service;

import com.ssafy.enjoytrip_springboot.board.command.mapper.BoardCommandMapper;
import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.board.common.exception.BoardException;
import com.ssafy.enjoytrip_springboot.board.query.mapper.BoardQueryMapper;
import com.ssafy.enjoytrip_springboot.common.util.PageNavigation;
import com.ssafy.enjoytrip_springboot.common.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardQueryServiceImpl implements BoardQueryService {

    private final BoardQueryMapper query;
    private final BoardCommandMapper command;

    @Override
    public List<BoardDto> listArticle(Map<String, Object> map) {

        try{

            log.info("service boardList before : ");
            List<BoardDto> boardDtos = query.listArticle(map);
            log.info("service boardList after : " + boardDtos);


            return boardDtos;
        } catch(SQLException e){
            throw new BoardException("listArticle 에러");
        }

    }

    // 정렬 관련 기능
    private Comparator<? super BoardDto> makeComparator(String sortKey, String direct) {
        Comparator<BoardDto> c = null;

        switch (sortKey) {
            case "1":
                c = Comparator.comparing(BoardDto::getUserId);
                break;
			case "2":
				c = Comparator.comparing(BoardDto::getRegisterTime);
				break;
			case "3":
				c = Comparator.comparing(BoardDto::getTitle);
				break;
			case "4":
				c = Comparator.comparing(BoardDto::getHit);
				break;
            default:
                c = Comparator.comparing(BoardDto::getNo);
                break;
        }

		return direct.equals("desc") ? c.reversed() : c;
    }

//    @Override
//    public PageNavigation makePageNavigation(Map<String, String> map) {
//
//        PageNavigation pageNavigation = new PageNavigation();
//
//        int naviSize = SizeConstant.NAVIGATION_SIZE;
//        int sizePerPage = SizeConstant.LIST_SIZE;
//        int currentPage = Integer.parseInt(map.get("pgno"));
//
//        pageNavigation.setCurrentPage(currentPage);
//        pageNavigation.setNaviSize(naviSize);
//        Map<String, Object> param = new HashMap<String, Object>();
//        String key = map.get("key");
////		if ("userid".equals(key))
////			key = "user_id";
//        param.put("key", key.isEmpty() ? "" : key);
//        param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//
//        try{
//            int totalCount = query.getTotalArticleCount(param);
//            pageNavigation.setTotalCount(totalCount);
//            int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//            pageNavigation.setTotalPageCount(totalPageCount);
//            boolean startRange = currentPage <= naviSize;
//            pageNavigation.setStartRange(startRange);
//            boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//            pageNavigation.setEndRange(endRange);
//            pageNavigation.makeNavigator();
//
//            return pageNavigation;
//        }
//        catch (SQLException e){
//            throw new BoardException("makePageNavigation 에러");
//        }
//    }

    @Override
    @Transactional
    public BoardDto getArticle(int articleNo){

        try{
            BoardDto result = query.getArticle(articleNo)
                    .orElseThrow(() -> new BoardException("조회할 수 없습니다."));
            command.updateHit(articleNo);
            log.info("result = " + result);
            return result;
        }
        catch(SQLException e){
            throw new BoardException("getArticle 에러");
        }
    }

    @Override
    public void updateHit(int articleNo) {
        try{
            command.updateHit(articleNo);
        } catch(SQLException e){
            throw new BoardException("updateHit 에러");
        }

    }

    @Override
    public int getTotalArticleCount(Map<String, Object> param){
        try {
            int count = query.getTotalArticleCount(param);
            return (count - 1) /  SizeConstant.LIST_SIZE + 1;
        } catch (SQLException e) {
            throw new BoardException("해당 하는 게시글이 없음");
        }
    }

}
