package com.example.trytwo.services;

import com.example.trytwo.Dto.RolesInfoDto;
import com.example.trytwo.models.Role;

import java.util.Optional;

public interface RoleService
{
    RolesInfoDto getRole(String id);
    void createRole(RolesInfoDto role);
}
