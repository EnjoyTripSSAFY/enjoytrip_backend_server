package com.ssafy.enjoytrip_springboot.board.query.mapper;

import com.ssafy.enjoytrip_springboot.board.common.dto.BoardDto;
import com.ssafy.enjoytrip_springboot.board.common.dto.ReplyDto;
import com.ssafy.enjoytrip_springboot.board.query.dto.ReplySingleRequest;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ReplyQueryMapper {

	List<ReplyDto> getReplyList(int no) throws SQLException;

	Optional<ReplyDto> getReply(ReplySingleRequest request) throws SQLException;
}
