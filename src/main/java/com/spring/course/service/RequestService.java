package com.spring.course.service;

import com.spring.course.domain.Request;

import java.util.List;

public interface RequestService {

    Request save(Request request);
    Request update(Request request);
    Request getById(Long id);
    List<Request> listaAll();
    List<Request> listaAllByOwnerId(Long ownerId);

}
