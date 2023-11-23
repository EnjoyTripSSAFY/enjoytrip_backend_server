package com.ssafy.enjoytrip_springboot.member.command.service;

import com.ssafy.enjoytrip_springboot.member.command.dto.request.ChangeRoleDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.JoinMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.LoginRequestDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.UpdateMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.response.LoginResponseDto;

public interface MemberCommandService {

    int idCheck(String userId);
    int joinMember(JoinMemberDto joinMemberDto);
//    LoginResponseDto loginMember(LoginRequestDto loginDto);

    int updateMember(UpdateMemberDto updateMemberDto);
    int deleteMember(String userId);

    int changeRole(ChangeRoleDto changeRoleDto);
}
