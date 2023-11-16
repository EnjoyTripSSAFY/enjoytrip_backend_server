package com.ssafy.enjoytrip_springboot.board.query.mapper;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.board.query.dto.PageOpDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface BoardQueryMapper {

	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	Optional<BoardDto> getArticle(int articleNo) throws SQLException;

}
