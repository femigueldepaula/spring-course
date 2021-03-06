package com.spring.course.dto;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserUpdateDto {

    @NotBlank(message = "Name required")
    private String name;

    @Email(message = "Email required")
    private String email;

    @Size(min = 7, max = 99, message = "Password must be between 7 and 99")
    private String password;

    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> stages = new ArrayList<>();
}
