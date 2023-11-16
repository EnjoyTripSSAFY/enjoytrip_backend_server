package com.ssafy.enjoytrip_springboot.member.query.service;

import com.ssafy.enjoytrip_springboot.member.common.exception.MemberException;
import com.ssafy.enjoytrip_springboot.member.query.dto.response.GetMemberDto;
import com.ssafy.enjoytrip_springboot.member.query.mapper.MemberQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberQueryMapper queryMapper;

    @Override
    public GetMemberDto getMember(String userId) {

        try{
            GetMemberDto member = queryMapper.getMember(userId);
            if(member == null){
                throw new MemberException("no member found");
            }

            return member;
        } catch (SQLException e) {
            throw new MemberException("getMember error");
        }
    }

    @Override
    public int idCheck(String userId) throws SQLException {

        try{
            return queryMapper.idCheck(userId);
        } catch(SQLException e){
            throw new MemberException("idCheck error");
        }
    }
}
