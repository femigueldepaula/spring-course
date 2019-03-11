package com.spring.course.controller;

import com.spring.course.converter.RequestStageConverter;
import com.spring.course.domain.RequestStage;
import com.spring.course.dto.RequestStageSavedto;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestStageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("request-stages")
@Slf4j
public class RequestStageController {

    @Autowired
    private RequestStageService requestStageService;

    @Autowired
    private RequestStageConverter requestStageConverter;


    @PostMapping
    public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSavedto requestStagedto){

        RequestStage stage = requestStageConverter.convertToRequestStage(requestStagedto);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestStageService.save(stage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageModel<RequestStage>> getById(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size,
                                                           @PathVariable("id") Long id){
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<RequestStage> requestStagePageModel = requestStageService.listAllByRequestIdOnLazyMode(id, pageRequestModel);

        return ResponseEntity.ok(requestStagePageModel);
    }




}
