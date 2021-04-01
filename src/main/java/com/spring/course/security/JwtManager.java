package com.spring.course.security;

import com.spring.course.constant.SecurityConstant;
import com.spring.course.dto.UserLoginResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

    public UserLoginResponseDto createToken(String email, List<String> roles) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, SecurityConstant.JWT_EXP_DAYS);

        String jwt = Jwts.builder()
                .setSubject(email)
                .setExpiration(calendar.getTime())
                .claim(SecurityConstant.JWT_ROLE_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.API_KEY)
                .compact();

        return UserLoginResponseDto.builder()
                .token(jwt)
                .exprireIn(calendar.getTimeInMillis())
                .tokenProvider(SecurityConstant.JWT_PROVIDER)
                .build();
    }

    public Claims parseToken(String jwt) throws JwtException {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstant.API_KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
