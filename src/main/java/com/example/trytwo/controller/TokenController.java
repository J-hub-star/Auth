package com.example.trytwo.controller;

import com.example.trytwo.Dto.TokenDTO;
import com.example.trytwo.services.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tokens")
public class TokenController
{
    @Autowired
    TokenService tokenService;
    @GetMapping("/validate")
    public void validateToken(HttpServletRequest HttpServletRequest) throws Exception
    {
        String jwt = "";
        tokenService.validateToken(jwt);
    }
}
