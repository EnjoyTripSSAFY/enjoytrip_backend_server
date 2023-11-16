package com.ssafy.enjoytrip_springboot.board.command.service;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;

public interface BoardCommandService {

	Long writeArticle(BoardDto boardDto) throws Exception;
	long modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;

	int blockArticle(int no) throws Exception;

}
