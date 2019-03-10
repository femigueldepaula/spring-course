package com.spring.course.service;

import com.spring.course.domain.User;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user);
    User getById(Long id);
    List<User> listAll();
    User login(String email, String password);
    PageModel<User> listAllOnLazyMode(PageRequestModel pageRequestModel);
}
