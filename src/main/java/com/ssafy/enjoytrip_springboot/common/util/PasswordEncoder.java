package com.ssafy.enjoytrip_springboot.common.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    public static String encodePassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

