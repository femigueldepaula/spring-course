package com.spring.course.controller;

import com.spring.course.converter.UserConverter;
import com.spring.course.domain.Request;
import com.spring.course.domain.User;
import com.spring.course.dto.UserLoginDto;
import com.spring.course.dto.UserSaveDto;
import com.spring.course.dto.UserUpdateDto;
import com.spring.course.dto.UserUpdateRoleDto;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestService;
import com.spring.course.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserConverter userConverter;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userDto){

        User user = userConverter.convertToUser(userDto);
        User createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateDto userUpdateDto){

        User user = userConverter.convertToUser(userUpdateDto);
        user.setId(id);
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(name = "id") Long id ){
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size){
        PageRequestModel pageRequestModel = new PageRequestModel(page, size);

        return ResponseEntity.ok(userService.listAllOnLazyMode(pageRequestModel));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto userLoginDto){
        return ResponseEntity.ok(userService.login(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsById(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                                                  @PathVariable("id") Long id){

        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> pageModel = requestService.listAllByOwnerIdOnLazyModel(id,pageRequestModel);
        return ResponseEntity.ok(pageModel);
    }

    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@RequestBody @Valid UserUpdateRoleDto userUpdateRoleDto,
                                        @PathVariable("id") Long id) {
        User user = User.builder()
                .id(id)
                .role(userUpdateRoleDto.getRole())
                .build();

        userService.updateRole(user);
        return ResponseEntity.ok().build();
    }
}
