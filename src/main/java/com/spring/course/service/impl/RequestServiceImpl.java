package com.spring.course.service.impl;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.domain.User;
import com.spring.course.domain.enums.RequestState;
import com.spring.course.exception.NotFoundException;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.repository.RequestRepository;
import com.spring.course.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return result.orElseThrow(() -> new NotFoundException("There are note request with id = " + id));
    }

    @Override
    public List<Request> listaAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> listaAllByOwnerId(Long ownerId) {
        return requestRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pageRequestModel){
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);
        PageModel<Request> pageModel = new PageModel<>(
                (int)page.getTotalElements(),
                page.getSize(),
                page.getTotalPages(),
                page.getContent()
        );
        return pageModel;
    }

    @Override
    public PageModel<Request> listAllOnLazyMode(PageRequestModel pageRequestModel){
        Pageable pageble = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<Request> page = requestRepository.findAll(pageble);

        PageModel<Request> pageModel = new PageModel<>(
                (int)page.getTotalElements(),
                page.getSize(),
                page.getTotalPages(),
                page.getContent()
        );

        return pageModel;
    }
}
