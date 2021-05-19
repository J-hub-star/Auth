package com.example.trytwo.services.implmentation;

import com.example.trytwo.Dto.UserInfoDto;
import com.example.trytwo.Dto.UserLoginDto;
import com.example.trytwo.models.Role;
import com.example.trytwo.models.User;
import com.example.trytwo.repository.RoleRepo;
import com.example.trytwo.repository.UserRepo;
import com.example.trytwo.services.TokenService;
import com.example.trytwo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder BPE;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    TokenService tokenService;


    @Override
    public void createUser(UserInfoDto uid)
    {
        User user = modelMapper.map(uid,User.class);
        user.setPassword(BPE.encode(uid.getPassword()));
        user.setRole(roleRepo.findAll().stream().map(Role::getRole).filter(role -> role.contains("USER")).collect(Collectors.toList()));
        tokenService.generateToken(user);
        userRepo.save(user);
        modelMapper.map(user,uid);
    }

    @Override
    public UserInfoDto loginUser(UserLoginDto ULD)
    {
        Optional<User> users = userRepo.findByUsername(ULD.getUsername());
        if(users.isPresent())
        {
            User user = users.get();
            //checking passwords
            if(BPE.matches(ULD.getPassword(),user.getPassword()))
            {
                return modelMapper.map(user,UserInfoDto.class);
            }
            else
                {
                    throw new BadCredentialsException("Invalid Input");
                }
        }else
        {
            throw new BadCredentialsException("Invalid Input");
        }

    }

    @Override
    public UserInfoDto getUserById(String id)
    {
        Optional<User> users = userRepo.findById(id);
        if(users.isPresent())
        {
            return modelMapper.map(users,UserInfoDto.class);
        }
        else
            {
                return null;
            }

    }

}
