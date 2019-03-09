package com.spring.course.controller;

import com.spring.course.domain.RequestStage;
import com.spring.course.service.RequestStageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("request-stages")
@Slf4j
public class RequestStageController {

    @Autowired
    private RequestStageService requestStageService;


    @PostMapping
    public ResponseEntity<RequestStage> save(@RequestBody RequestStage stage){
        return ResponseEntity.status(HttpStatus.CREATED).body(requestStageService.save(stage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(requestStageService.getById(id));
    }




}
