package com.example.trytwo.controller;

import com.example.trytwo.Dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController
{

    @PostMapping("/register")
    public UserInfoDto createUser(@RequestBody UserInfoDto userInfoDto)
    {
        return null;
    }
}
