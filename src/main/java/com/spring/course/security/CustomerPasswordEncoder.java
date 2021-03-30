package com.spring.course.security;

import com.spring.course.util.HashUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String hash = HashUtil.getSecureHash(rawPassword.toString());
        return hash;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        String hash = HashUtil.getSecureHash(rawPassword.toString());
        return hash.equals(encodePassword);
    }
}
