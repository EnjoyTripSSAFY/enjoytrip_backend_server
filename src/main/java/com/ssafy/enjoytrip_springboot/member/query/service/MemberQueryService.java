package com.ssafy.enjoytrip_springboot.member.query.service;

import com.ssafy.enjoytrip_springboot.member.query.dto.response.GetMemberDto;

import java.sql.SQLException;

public interface MemberQueryService {

    GetMemberDto getMember(String userId);

    int idCheck(String userId) throws SQLException;
}
