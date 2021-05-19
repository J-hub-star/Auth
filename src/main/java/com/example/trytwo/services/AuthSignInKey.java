package com.example.trytwo.services;

import io.jsonwebtoken.SigningKeyResolver;

import javax.crypto.SecretKey;

public interface AuthSignInKey extends SigningKeyResolver
{
    SecretKey getSecretKey();
}
