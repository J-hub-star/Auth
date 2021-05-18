package com.example.trytwo.controller;

import com.example.trytwo.Dto.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tokens")
public class TokenController
{
    @GetMapping("/validate")
    public void validateToken(HttpServletRequest HttpServletRequest) throws Exception {
        throw new Exception("Some rando");
    }
}
