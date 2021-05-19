package com.example.trytwo.config;

import com.example.trytwo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

public class AuthProvider extends AbstractUserDetailsAuthenticationProvider
{
    @Autowired
    UserRepo userRepo;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        final String cre = (String) authentication.getCredentials();
        if(isEmpty(cre))
        {
            return new User(username,"", AuthorityUtils.createAuthorityList("ROLE_ANON"));
        }
        Optional<com.example.trytwo.models.User> user = userRepo.findByToken(cre);
        return null;
    }
}
