package com.example.trytwo.services;

import com.example.trytwo.Dto.UserInfoDto;
import com.example.trytwo.Dto.UserLoginDto;

public interface UserService
{
    void createUser(UserInfoDto uid);
    UserInfoDto loginUser(UserLoginDto ULD);
    UserInfoDto getUserById(String id);

}
