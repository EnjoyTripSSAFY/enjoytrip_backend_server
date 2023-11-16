package com.ssafy.enjoytrip_springboot.board.query.service;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.common.util.PageNavigation;

import java.util.List;
import java.util.Map;

public interface BoardQueryService {

	List<BoardDto> listArticle(Map<String, Object> map);
//	PageNavigation makePageNavigation(Map<String, String> map);
	BoardDto getArticle(int articleNo);
	void updateHit(int articleNo);
	int getTotalArticleCount(Map<String, Object> param);


}
