package com.spring.course.converter;

import com.spring.course.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter<T> {

    @Autowired
    private ModelMapper modelMapper;

    public User convertToUser(T object){

        return modelMapper.map(object, User.class);
    }

}
