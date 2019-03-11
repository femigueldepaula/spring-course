package com.spring.course.dto;

import com.spring.course.domain.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRoleDto {

    @NotNull(message = "Role required")
    private Role role;
}
