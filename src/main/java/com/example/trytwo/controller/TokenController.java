package com.example.trytwo.controller;

import com.example.trytwo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.EMPTY;

@RestController
@RequestMapping("/api/tokens")
public class TokenController
{
    @Autowired
    TokenService tokenService;
    @GetMapping("/validate")
    public void validateToken(HttpServletRequest HttpServletRequest) throws Exception
    {
        String auth = HttpServletRequest.getHeader(AUTHORIZATION);
        String jwt = null;
        if(!ObjectUtils.isEmpty(auth)){jwt = auth.split("\\s")[1];

        }
        tokenService.validateToken(jwt);
    }
}
