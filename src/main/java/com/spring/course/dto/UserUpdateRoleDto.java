package com.spring.course.dto;

import com.spring.course.domain.enums.Role;
import lombok.Data;

@Data
public class UserUpdateRoleDto {
    private Role role;
}
