package com.ssafy.enjoytrip_springboot.auth.mapper;

import com.ssafy.enjoytrip_springboot.auth.dto.SaveRefreshTokenDto;
import com.ssafy.enjoytrip_springboot.auth.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface AuthMapper {


    int saveRefreshToken(SaveRefreshTokenDto saveRefreshTokenDto) throws SQLException;

    String getRefreshToken(String userId) throws SQLException;

    void removeRefreshToken(String userId) throws SQLException;

    UserInfoDto getInfo(String userId) throws SQLException;
}
