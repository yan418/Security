package com.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {


    private String encryptPassword(String password) {
        // BCryptPasswordEncoder 加密
        return new BCryptPasswordEncoder().encode(password);
    }

    public static void main(String[] args) {

        // String s = new BCrypt().encryptPassword("123456");
        // System.out.println(s);
    }

}
