package com.ssafy.enjoytrip_springboot.auth.service;

import com.ssafy.enjoytrip_springboot.auth.dto.UserAuthDto;

public interface AuthService {

    UserAuthDto signin(String userId, String password);
    void saveRefreshToken(String userId, String refreshToken);
    String getRefreshToken(String userId);
    void logout(String email);
}
