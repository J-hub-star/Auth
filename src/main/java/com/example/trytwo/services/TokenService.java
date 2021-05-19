package com.example.trytwo.services;

import com.example.trytwo.config.InvalidException;
import com.example.trytwo.models.User;

public interface TokenService
{
    void validateToken(String s) throws InvalidException;

    void generateToken(User user);
}
