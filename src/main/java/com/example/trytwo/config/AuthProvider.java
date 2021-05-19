package com.example.trytwo.config;

import com.example.trytwo.repository.UserRepo;
import com.example.trytwo.services.TokenService;
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

    @Autowired
    TokenService tokenService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        final String cre = (String) authentication.getCredentials();

        if(isEmpty(cre))
        {
            return new User(username,"", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        }
        Optional<com.example.trytwo.models.User> user = userRepo.findByToken(cre);
        final com.example.trytwo.models.User user2 = user.get();

            try {
                tokenService.validateToken(cre);
            } catch (InvalidException e) {
                user2.setToken(null);
                userRepo.save(user2);
            }


        if(user.isPresent())
        {

            return new User(username,"",AuthorityUtils.createAuthorityList(user2.getRole()
                    .stream().map(roleName -> "role_"+roleName)
                    .toArray(String []:: new)));
        }else
            {
                throw new TokenExceptionMarkdown("Invalid Token");
            }

    }
}
