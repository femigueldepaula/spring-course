package com.spring.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class UserLoginResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;
    private Long exprireIn;
    private String tokenProvider;
}
