package com.spring.course.service;

import com.spring.course.domain.Request;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;

import java.util.List;

public interface RequestService {

    Request save(Request request);
    Request update(Request request);
    Request getById(Long id);
    List<Request> listaAll();
    List<Request> listaAllByOwnerId(Long ownerId);
    PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pageRequestModel);
    PageModel<Request> listAllOnLazyMode(PageRequestModel pageRequestModel);

}
