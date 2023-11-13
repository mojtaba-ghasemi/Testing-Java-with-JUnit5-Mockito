package com.azin.testing.java.with.junit5.mockito.ui.controller;

import com.azin.testing.java.with.junit5.mockito.model.UserDto;
import com.azin.testing.java.with.junit5.mockito.service.UserService;
import com.azin.testing.java.with.junit5.mockito.ui.request.UserDetailRequestModel;
import com.azin.testing.java.with.junit5.mockito.ui.response.UserResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = "/createUser")
    public UserResponse createUser(@RequestBody @Valid UserDetailRequestModel userDetailRequestModel){

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetailRequestModel , UserDto.class);

        userDto = userService.createUser(userDto);
        return modelMapper.map(userDto , UserResponse.class);
    }
}
