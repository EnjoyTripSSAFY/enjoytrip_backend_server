package com.ssafy.enjoytrip_springboot.board.query.service;

import com.ssafy.enjoytrip_springboot.board.query.dto.ReplyResponseDTO;

import java.sql.SQLException;
import java.util.List;

public interface ReplyQueryService {
    List<ReplyResponseDTO> getReplies(int no) throws SQLException;
}
