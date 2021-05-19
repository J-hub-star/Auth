package com.example.trytwo.services.implmentation;

import com.example.trytwo.Dto.RolesInfoDto;
import com.example.trytwo.models.Role;
import com.example.trytwo.repository.RoleRepo;
import com.example.trytwo.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public RolesInfoDto getRole(String id)
    {
        Optional<Role> roles = roleRepo.findById(id);
        if(roles.isPresent())
        {
            Role role = roles.get();
            return modelMapper.map(role,RolesInfoDto.class);
        }else
            {
                return null;
            }

    }

    @Override
    public void createRole(RolesInfoDto roleDTO)
    {
        Role role = modelMapper.map(roleDTO,Role.class);
        roleRepo.save(role);
        modelMapper.map(role,roleDTO);

    }
}
