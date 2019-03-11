package com.spring.course.converter;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestStageConverter<T> {

    @Autowired
    private ModelMapper modelMapper;

    public RequestStage convertToRequestStage(T object){

        return modelMapper.map(object, RequestStage.class);
    }

}
