package com.spring.course.service.impl;

import com.spring.course.domain.User;
import com.spring.course.exception.NotFoundException;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.repository.UserRepository;
import com.spring.course.service.UserService;
import com.spring.course.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        user.setPassword(HashUtil.getSecureHash(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        user.setPassword(HashUtil.getSecureHash(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow(() -> new NotFoundException("There are note user with id = " + id));
    }

    @Override
    public List<User> listAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User login(String email, String password) {
        password = HashUtil.getSecureHash(password);
        Optional<User> result = userRepository.login(email, password);
        return result.get();
    }

    @Override
    public PageModel<User> listAllOnLazyMode(PageRequestModel pageRequestModel){
        Pageable pageble = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<User> page = userRepository.findAll(pageble);

        PageModel<User> pageModel = new PageModel<>(
                (int)page.getTotalElements(),
                page.getSize(),
                page.getTotalPages(),
                page.getContent()
        );

        return pageModel;
    }

    @Override
    public int updateRole(User user){
        return userRepository.updateRole(user.getId(), user.getRole());
    }
}
