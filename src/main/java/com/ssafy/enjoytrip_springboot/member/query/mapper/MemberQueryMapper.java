package com.ssafy.enjoytrip_springboot.member.query.mapper;

import com.ssafy.enjoytrip_springboot.member.query.dto.response.GetMemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface MemberQueryMapper {

    GetMemberDto getMember(String userId) throws SQLException;

    int idCheck(String userId) throws SQLException;
}
