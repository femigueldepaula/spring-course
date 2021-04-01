package com.spring.course.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.course.constant.SecurityConstant;
import com.spring.course.controller.exception.ApiError;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String jwt = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(jwt == null || !jwt.startsWith(SecurityConstant.JWT_PROVIDER)) {
            buildResponseUnauthorizedError(SecurityConstant.JWT_INVALID_MSG, httpServletResponse);
            return;
        }
        jwt = jwt.replace(SecurityConstant.JWT_PROVIDER, "");

        try {
            Claims claims = jwtManager.parseToken(jwt);
            String email = claims.getSubject();
            List<String> roles = (List<String>) claims.get(SecurityConstant.JWT_ROLE_KEY);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

            roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));

            Authentication authentication = new UsernamePasswordAuthenticationToken(email,null, grantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            buildResponseUnauthorizedError(e.getMessage(), httpServletResponse);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void buildResponseUnauthorizedError(
        String message,
        HttpServletResponse httpServletResponse
    ) throws IOException {

        ApiError apiError = ApiError.builder()
            .code(HttpStatus.UNAUTHORIZED.value())
            .message(message)
            .date(new Date())
            .build();

        PrintWriter writer = httpServletResponse.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String apiErrorString = mapper.writeValueAsString(apiError);

        writer.write(apiErrorString);

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
