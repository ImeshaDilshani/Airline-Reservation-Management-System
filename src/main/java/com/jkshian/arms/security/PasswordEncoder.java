package com.jkshian.arms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class PasswordEncoder {
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }


}