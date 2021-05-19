package com.example.trytwo.config;

import com.example.trytwo.models.User;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.ObjectUtils.isEmpty;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request,response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error",failed.getCause());
        jsonObject.put("errorMessage",failed.getMessage());
        response.getWriter().print(jsonObject);
        response.getWriter().flush();
    }

    public AuthFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public AuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenUnStripped = request.getHeader(AUTHORIZATION);
        String token = StringUtils.removeStart(Optional.ofNullable(tokenUnStripped).orElse(""), "Bearer").trim();
        Authentication authentication;
        if(isEmpty(token)){
            authentication = new UsernamePasswordAuthenticationToken("guest","");
        }else
            {
                authentication = new UsernamePasswordAuthenticationToken("user",token);
            }

        return getAuthenticationManager().authenticate(authentication);

    }
}
