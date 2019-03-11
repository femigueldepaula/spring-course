package com.spring.course.controller;

import com.spring.course.converter.RequestConverter;
import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.dto.RequestSavedto;
import com.spring.course.dto.RequestUpdatedto;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestService;
import com.spring.course.service.RequestStageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("requests")
@Slf4j
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestStageService requestStageService;

    @Autowired
    private RequestConverter requestConverter;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid RequestSavedto requestdto){

        Request request = requestConverter.convertToRequest(requestdto);
        Request createdRequest = requestService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody @Valid RequestUpdatedto requestUpdatedto){

        Request request = requestConverter.convertToRequest(requestUpdatedto);
        request.setId(id);
        return ResponseEntity.ok(requestService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(requestService.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageModel<Request>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> pageModel = requestService.listAllOnLazyMode(pageRequestModel);

        return ResponseEntity.ok(pageModel);
    }

    @GetMapping("/{id}/request-stages")
    public ResponseEntity<List<RequestStage>> listaAllStagesById(@PathVariable("id") Long id){
        return ResponseEntity.ok(requestStageService.listAllByRequestId(id));
    }
}
