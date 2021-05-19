package com.example.trytwo.services.implmentation;

import com.example.trytwo.config.InvalidException;
import com.example.trytwo.models.User;
import com.example.trytwo.services.AuthSignInKey;
import com.example.trytwo.services.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SignatureException;

@Service
public class TokenServiceImpl implements TokenService
{

    @Autowired
    AuthSignInKey authSignInKey;

    @Override
    public void validateToken(String s) throws InvalidException {
        try
        {
            Jwts.parser().setSigningKeyResolver(authSignInKey).parse(s);
        }
        catch (ExpiredJwtException | IllegalArgumentException | MalformedJwtException e)
        {
            throw new InvalidException(e);
        }

    }

    @Override
    public void generateToken(User user)
    {
        String jwt;
        jwt = Jwts.builder().setSubject(user.getUsername()).setAudience(user.getRole().toString())
        .signWith(authSignInKey.getSecretKey(), SignatureAlgorithm.HS512).compact();
        user.setToken(jwt);
    }
}
