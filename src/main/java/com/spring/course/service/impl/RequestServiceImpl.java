package com.spring.course.service.impl;

import com.spring.course.domain.Request;
import com.spring.course.domain.enums.RequestState;
import com.spring.course.repository.RequestRepository;
import com.spring.course.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request save(Request request) {
        request.setState(RequestState.OPEN);
        request.setCreationDate(new Date());

        return requestRepository.save(request);
    }

    @Override
    public Request update(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request getById(Long id) {
        Optional<Request> result = requestRepository.findById(id);
        return result.get();
    }

    @Override
    public List<Request> listaAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> listaAllByOwnerId(Long ownerId) {
        return requestRepository.findAllByOwnerId(ownerId);
    }
}
