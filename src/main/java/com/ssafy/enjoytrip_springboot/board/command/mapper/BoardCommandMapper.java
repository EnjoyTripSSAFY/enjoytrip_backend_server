package com.ssafy.enjoytrip_springboot.board.command.mapper;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

@Mapper
public interface BoardCommandMapper {

	Long writeArticle(BoardDto boardDto) throws SQLException;
	void updateHit(int no) throws SQLException;

	Long modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int no) throws SQLException;

	void blockArticle(int no) throws SQLException;

}
