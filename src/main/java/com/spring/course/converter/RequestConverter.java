package com.spring.course.converter;

import com.spring.course.domain.Request;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestConverter<T> {

    @Autowired
    private ModelMapper modelMapper;

    public Request convertToRequest(T object){

        return modelMapper.map(object, Request.class);
    }
}
