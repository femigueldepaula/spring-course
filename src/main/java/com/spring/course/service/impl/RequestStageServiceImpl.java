package com.spring.course.service.impl;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.exception.NotFoundException;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.repository.RequestRepository;
import com.spring.course.repository.RequestStageRepository;
import com.spring.course.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStageServiceImpl implements RequestStageService {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public RequestStage save(RequestStage stage) {
        stage.setRealizationDate(new Date());
        RequestStage createdStage = requestStageRepository.save(stage);
        requestRepository.updateStatus(stage.getId(), stage.getState());

        return createdStage;
    }

    @Override
    public RequestStage getById(Long id) {
        Optional<RequestStage> result = requestStageRepository.findById(id);
        return result.orElseThrow(() -> new NotFoundException("There are note stage with id = " + id));
    }

    @Override
    public List<RequestStage> listAllByRequestId(Long requestId) {
        return requestStageRepository.findAllByRequestId(requestId);
    }

    @Override
    public PageModel<RequestStage> listAllByRequestIdOnLazyMode(Long requestId, PageRequestModel pageRequestModel){
        Pageable pageble = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<RequestStage> page = requestStageRepository.findAllByRequestId(requestId, pageble);

        PageModel<RequestStage> pageModel = new PageModel<>(
                (int)page.getTotalElements(),
                page.getSize(),
                page.getTotalPages(),
                page.getContent()
        );

        return pageModel;
    }
}
