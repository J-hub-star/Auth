package com.example.trytwo.config;

import org.springframework.security.core.AuthenticationException;

public class TokenExceptionMarkdown extends AuthenticationException {
    public TokenExceptionMarkdown(String s)
    {
        super(s);
    }
}
