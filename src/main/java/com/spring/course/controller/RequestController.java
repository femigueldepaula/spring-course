package com.spring.course.controller;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestService;
import com.spring.course.service.RequestStageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("requests")
@Slf4j
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody Request request){
        Request createdRequest = requestService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable("id") Long id, @RequestBody Request request){
        request.setId(id);
        return ResponseEntity.ok(requestService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(requestService.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageModel<Request>> listAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> pageModel = requestService.listAllOnLazyMode(pageRequestModel);

        return ResponseEntity.ok(pageModel);
    }

    @GetMapping("/{id}/request-stages")
    public ResponseEntity<List<RequestStage>> listaAllStagesById(@PathVariable("id") Long id){
        return ResponseEntity.ok(requestStageService.listAllByRequestId(id));
    }
}
