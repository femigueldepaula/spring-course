package com.spring.course.security;

import com.spring.course.domain.Request;
import com.spring.course.domain.User;
import com.spring.course.service.RequestService;
import com.spring.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("accessManager")
public class AccessManager {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    public boolean isOwner(Long id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getById(id);
        return email.equals(user.getEmail());
    }

    public boolean isRequestOwner(Long id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getById(id);
        Request request = requestService.getById(id);
        return user.getId() == request.getOwner().getId();
    }
}
