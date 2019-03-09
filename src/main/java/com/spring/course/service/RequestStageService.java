package com.spring.course.service;

import com.spring.course.domain.RequestStage;

import java.util.List;

public interface RequestStageService {

    RequestStage save(RequestStage requestStage);
    RequestStage getById(Long id);
    List<RequestStage> listAllByRequestId(Long requestId);

}
