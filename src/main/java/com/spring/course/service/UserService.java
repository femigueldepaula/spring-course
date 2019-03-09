package com.spring.course.service;

import com.spring.course.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user);
    User getById(Long id);
    List<User> listAll();
    User login(String email, String password);
}
