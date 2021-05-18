package com.example.trytwo.controller;

import com.example.trytwo.Dto.UserInfoDto;
import com.example.trytwo.Dto.UserLoginDto;
import com.example.trytwo.models.User;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController
{

    @PostMapping("/register")
    public UserInfoDto createUser(@RequestBody UserInfoDto userInfoDto)
    {
        Preconditions.checkNotNull(userInfoDto);
        System.out.println(userInfoDto.getUsername());
        System.out.println(userInfoDto.getRole());
        return userInfoDto;
    }

    @GetMapping("/userInformation/{user_id}")
    public UserInfoDto getUserInfo(@PathVariable String user_id)
    {
        return null;
    }

    @PostMapping("/login")
    public UserLoginDto loginUser(@RequestBody UserLoginDto userLoginDto)
    {
        Preconditions.checkNotNull(userLoginDto);
        System.out.println(userLoginDto.getUsername());
        System.out.println(userLoginDto.getPassword());
        return null;
    }
}
