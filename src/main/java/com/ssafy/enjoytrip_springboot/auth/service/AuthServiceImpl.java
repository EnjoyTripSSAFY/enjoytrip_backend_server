package com.ssafy.enjoytrip_springboot.auth.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip_springboot.auth.dto.SaveRefreshTokenDto;
import com.ssafy.enjoytrip_springboot.auth.dto.TokenDto;
import com.ssafy.enjoytrip_springboot.auth.dto.UserAuthDto;
import com.ssafy.enjoytrip_springboot.auth.dto.UserInfoDto;
import com.ssafy.enjoytrip_springboot.auth.jwt.JwtTokenProvider;
import com.ssafy.enjoytrip_springboot.auth.mapper.AuthMapper;
import com.ssafy.enjoytrip_springboot.member.command.mapper.MemberCommandMapper;
import com.ssafy.enjoytrip_springboot.member.common.exception.MemberException;
import com.ssafy.enjoytrip_springboot.member.query.dto.response.GetMemberDto;
import com.ssafy.enjoytrip_springboot.member.query.mapper.MemberQueryMapper;
import com.ssafy.enjoytrip_springboot.member.query.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberQueryMapper memberQueryMapper;

    @Transactional
    public TokenDto login(String memberId, String password) {

        try {

            GetMemberDto find = memberQueryMapper.getMember(memberId);

            // 탈퇴여부가 false인 경우
            if(find.getWithdrawalYn() == 0){

                // Login ID/PW 를 기반으로 Authentication 객체 생성
                // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
                System.out.println("Login ID/PW 를 기반으로 Authentication 객체 생성");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

                // 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
                // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
                System.out.println("실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분");
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

                // 인증 정보를 기반으로 JWT 토큰 생성
                System.out.println("3. 인증 정보를 기반으로 JWT 토큰 생성");
                TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

                SaveRefreshTokenDto saveRefreshTokenDto = SaveRefreshTokenDto.builder()
                        .refreshToken(tokenDto.getRefreshToken())
                        .userId(memberId).build();

                authMapper.saveRefreshToken(saveRefreshTokenDto);
                return tokenDto;
            } else{
                throw new MemberException("이미 탈퇴된 회원입니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("login 중 에러");
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