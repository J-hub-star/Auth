package com.example.trytwo.controller;

import com.example.trytwo.Dto.UserInfoDto;
import com.example.trytwo.Dto.UserLoginDto;
import com.example.trytwo.models.User;
import com.example.trytwo.services.UserService;
import com.example.trytwo.services.implmentation.UserServiceImpl;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController
{
    @Autowired
    private UserService userService;

    public UserController()
    {

    }

    @PostMapping("/register")
    public UserInfoDto createUser(@RequestBody UserInfoDto userInfoDto)
    {
        Preconditions.checkNotNull(userInfoDto);
        userService.createUser(userInfoDto);
        return userInfoDto;
    }

    @GetMapping("/userInformation/{user_id}")
    public UserInfoDto getUserInfo(@PathVariable String user_id)
    {
        return userService.getUserById(user_id);
    }

    @PostMapping("/login")
    public UserInfoDto loginUser(@RequestBody UserLoginDto userLoginDto)
    {
        Preconditions.checkNotNull(userLoginDto);

        return userService.loginUser(userLoginDto);
    }
}
