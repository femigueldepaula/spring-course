package com.spring.course.controller;

import com.spring.course.domain.Request;
import com.spring.course.domain.User;
import com.spring.course.dto.UserLoginDto;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestService;
import com.spring.course.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> save(@PathVariable(name = "id") Long id, @RequestBody User user){
        user.setId(id);
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(name = "id") Long id ){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> listAll(@RequestParam("page") int page, @RequestParam("size") int size){
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);

        return ResponseEntity.ok(userService.listAllOnLazyMode(pageRequestModel));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok(userService.login(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsById(@RequestParam("page") int page, @RequestParam("size") int size, @PathVariable("id") Long id){

        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> pageModel = requestService.listAllByOwnerIdOnLazyModel(id,pageRequestModel);
        return ResponseEntity.ok(pageModel);
    }
}
