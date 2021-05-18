package com.example.trytwo.controller;

import com.example.trytwo.Dto.RolesInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleController
{
    @PostMapping("/addRoles")
    public RolesInfoDto addRoles(@RequestBody RolesInfoDto rolesInfoDto)
    {
        System.out.println(rolesInfoDto.getRole());
        return null;
    }

    @GetMapping("/roles/{role_id}")
    public RolesInfoDto getRole(@PathVariable String role_id)
    {
        return null;
    }
}
