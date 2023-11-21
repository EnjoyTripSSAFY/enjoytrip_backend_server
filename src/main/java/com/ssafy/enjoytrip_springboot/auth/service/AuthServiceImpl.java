package com.ssafy.enjoytrip_springboot.auth.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.enjoytrip_springboot.auth.dto.SaveRefreshTokenDto;
import com.ssafy.enjoytrip_springboot.auth.dto.UserAuthDto;
import com.ssafy.enjoytrip_springboot.auth.dto.UserInfoDto;
import com.ssafy.enjoytrip_springboot.auth.jwt.JwtUtil;
import com.ssafy.enjoytrip_springboot.auth.mapper.AuthMapper;
import com.ssafy.enjoytrip_springboot.member.query.dto.response.GetMemberDto;
import com.ssafy.enjoytrip_springboot.member.query.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final MemberQueryService queryService;
    private final AuthMapper authMapper;

    public UserAuthDto signin(String userId, String password) {

        GetMemberDto find = queryService.getMember(userId);

        if (userId.equals(find.getUserId()) && password.equals(find.getUserPassword())) {
            // 인증 성공 시 accessToken, refreshToken 생성
            String accessToken = jwtUtil.createAccessToken(userId);
            String refreshToken = jwtUtil.createRefreshToken();
            saveRefreshToken(userId, refreshToken);

            return UserAuthDto.builder().userId(userId).accessToken(accessToken).refreshToken(refreshToken).build();
        } else {
            throw new RuntimeException("해당 사용자는 없습니다.");
        }
    }

    /**
     * 사용자의 토큰 정보 저장
     */
    public void saveRefreshToken(String userId, String refreshToken) {

        SaveRefreshTokenDto saveRefreshTokenDto = SaveRefreshTokenDto.builder()
                .userId(userId)
                .refreshToken(refreshToken).build();

        try {
            int result = authMapper.saveRefreshToken(saveRefreshTokenDto);

        } catch(SQLException e) {
            throw new RuntimeException("refreshToken 저장 중 에러");
        }
    }

    public String getRefreshToken(String userId) {

        try {
            return authMapper.getRefreshToken(userId);

        } catch (SQLException e) {
            throw new RuntimeException("refreshToken 조회 에러");
        }
    }

    public void logout(String userId) {

        try{
            authMapper.removeRefreshToken(userId);
        } catch(SQLException e) {
            throw new RuntimeException("logout 에러");
        }
    }

    @Override
    public UserInfoDto getInfo(String userId) {

        try {
            return authMapper.getInfo(userId);
        } catch (SQLException e) {
            throw new RuntimeException("getInfo 에러");
        }
    }
}