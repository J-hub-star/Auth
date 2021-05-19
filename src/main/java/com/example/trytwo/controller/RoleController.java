package com.example.trytwo.controller;

import com.example.trytwo.Dto.RolesInfoDto;
import com.example.trytwo.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleController
{
    @Autowired
    RoleService roleService;

    @PostMapping("/addRoles")
    public void addRoles(@RequestBody RolesInfoDto rolesInfoDto)
    {
        roleService.createRole(rolesInfoDto);
    }

    @GetMapping("/roles/{role_id}")
    public RolesInfoDto getRole(@PathVariable String role_id)
    {
        return roleService.getRole(role_id);
    }
}
