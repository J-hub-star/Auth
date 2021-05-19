package com.example.trytwo.config;

import com.example.trytwo.Dto.UserInfoDto;
import com.example.trytwo.models.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class BeanConfig
{
    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        final TypeMap<User,UserInfoDto> typeMap =modelMapper.typeMap(User.class, UserInfoDto.class);
        typeMap.addMappings(mapping -> mapping.skip(UserInfoDto::setPassword));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthProvider authProvider(){return new AuthProvider();}

    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        configSource.registerCorsConfiguration("/**",config);
        return new CorsFilter(configSource);
    }
}
