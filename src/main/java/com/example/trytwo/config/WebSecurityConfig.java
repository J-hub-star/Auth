package com.example.trytwo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authProvider).addFilterBefore(authFilter(),AnonymousAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()
                .and()
        .csrf().disable()
                .httpBasic().disable()
                .logout().disable()
        .cors()
        ;
                ;

    }

    public AuthFilter authFilter() throws Exception {
        OrRequestMatcher orRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/users/**"),
                new AntPathRequestMatcher("/token/**"),
                new AntPathRequestMatcher("/role/**")
        );
        AuthFilter authFilter = new AuthFilter(orRequestMatcher);
        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;
    }




}
