package com.spring.course.controller;

import com.spring.course.converter.UserConverter;
import com.spring.course.domain.Request;
import com.spring.course.domain.User;
import com.spring.course.dto.*;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.security.JwtManager;
import com.spring.course.service.RequestService;
import com.spring.course.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired private UserService userService;

    @Autowired private RequestService requestService;

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private JwtManager jwtManager;

    @Autowired
    private UserConverter userConverter;

    @Secured({"ROLE_ADMINISTRATOR"})
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userDto){

        LOGGER.info("Cadastrando usuario -> " + userDto.getName());
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
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginDto userLoginDto){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(), userLoginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        List<String> roles = userSpring.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jwtManager.createToken(userSpring.getUsername(), roles));

    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsById(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                                                  @PathVariable("id") Long id){

        PageRequestModel pageRequestModel = new PageRequestModel(page, size);
        PageModel<Request> pageModel = requestService.listAllByOwnerIdOnLazyModel(id,pageRequestModel);
        return ResponseEntity.ok(pageModel);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
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
