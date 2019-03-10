package com.spring.course.service;

import com.spring.course.domain.RequestStage;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;

import java.util.List;

public interface RequestStageService {

    RequestStage save(RequestStage requestStage);
    RequestStage getById(Long id);
    List<RequestStage> listAllByRequestId(Long requestId);
    PageModel<RequestStage> listAllByRequestIdOnLazyMode(Long requestId, PageRequestModel pageRequestModel);

}
