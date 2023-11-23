package com.ssafy.enjoytrip_springboot.member.command.service;

import com.ssafy.enjoytrip_springboot.member.command.dto.request.ChangeRoleDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.JoinMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.dto.request.UpdateMemberDto;
import com.ssafy.enjoytrip_springboot.member.command.mapper.MemberCommandMapper;
import com.ssafy.enjoytrip_springboot.member.common.exception.MemberException;
import com.ssafy.enjoytrip_springboot.member.query.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberCommandServiceImpl implements MemberCommandService {

    private final PasswordEncoder passwordEncoder;
    private final MemberQueryService queryService;
    private final MemberCommandMapper commandMapper;

    @Override
    public int idCheck(String userId) {

        try{
            return commandMapper.idCheck(userId);
        } catch(SQLException e){
            throw new MemberException("idCheck error");
        }
    }

    @Override
    public int joinMember(JoinMemberDto joinMemberDto) {

        try{

            int check = queryService.idCheck(joinMemberDto.getUserId());
            if(check != 0) {
                throw new MemberException("해당 아이디는 이미 존재합니다.");
            }

            String encodedPassword = passwordEncoder.encode(joinMemberDto.getUserPassword());
            joinMemberDto.setUserPassword(encodedPassword);
            commandMapper.joinMember(joinMemberDto);

            // 유저 권한 생성
            commandMapper.grantRole(joinMemberDto.getUserId());

            return 1;
        } catch(SQLException e) {
            throw new MemberException("join error");
        }
    }

//    @Override
//    public LoginResponseDto loginMember(LoginRequestDto loginDto) {
//
//        GetMemberDto find = queryService.getMember(loginDto.getUserId());
//
//        if(loginDto.getUserPassword().equals(find.getUserPassword())){
//
//            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
//                    .userId(find.getUserId())
//                    .userName(find.getUserName())
//                    .emailId(find.getEmailId())
//                    .email_domain(find.getEmail_domain())
//                    .joinDate(find.getJoinedTime()).build();
//
//            return loginResponseDto;
//        }
//
//        throw new MemberException("회원 정보가 일치하지 않습니다.");
//    }


    @Override
    public int updateMember(UpdateMemberDto updateMemberDto) {

        try{
            int check = queryService.idCheck(updateMemberDto.getUserId());
            if(check != 1) {
                throw new MemberException("해당 id의 사용자가 없습니다.");
            }

            return commandMapper.updateMember(updateMemberDto);


        } catch(SQLException e) {
            throw new MemberException("update error");
        }

    }

    @Override
    public int deleteMember(String userId) {

        try{
            return commandMapper.deleteMember(userId);

        } catch(SQLException e) {
            throw new MemberException("delete error");
        }

    }

    @Override
    public int changeRole(ChangeRoleDto changeRoleDto) {
        try{
            return commandMapper.changeRole(changeRoleDto);

        } catch(SQLException e) {
            throw new MemberException("changeRole error");
        }
    }
}
