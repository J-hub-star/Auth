package com.example.trytwo.Dto;

import com.example.trytwo.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto
{
    private String username;
    private String token;
    private String password;
    private String email;
    private List<Role> role;
}
