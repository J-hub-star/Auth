package com.example.trytwo.services.implmentation;

import com.example.trytwo.services.AuthSignInKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import static java.util.Objects.isNull;

@Component
public class AuthSignInKeyImpl implements AuthSignInKey
{
    @Value("${jwt.secret.key}")
    String secretKet;

    private SecretKey secretKey;

    @Override
    public SecretKey getSecretKey()
    {
        if(isNull(secretKey))
        {
            this.secretKey = Keys.hmacShaKeyFor(Base64.getEncoder()
                    .encode(this.secretKet.getBytes(StandardCharsets.UTF_8)));
        }
        return this.secretKey;
    }

    @Override
    public Key resolveSigningKey(JwsHeader header, Claims claims) {
        return getSecretKey();
    }

    @Override
    public Key resolveSigningKey(JwsHeader header, String plaintext) {
        return getSecretKey();
    }
}
