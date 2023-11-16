package com.ssafy.enjoytrip_springboot.member.command.mapper;

import com.ssafy.enjoytrip_springboot.member.command.dto.request.JoinMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.LoginRequestDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.UpdateMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.response.LoginResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface MemberCommandMapper {

    int idCheck(String userId) throws SQLException;
    int joinMember(JoinMemberDto joinMemberDto) throws SQLException;
    LoginResponseDto loginMember(LoginRequestDto loginDto) throws SQLException;
    int updateMember(UpdateMemberDto member) throws SQLException;
    int deleteMember(String userId) throws SQLException;
}
