package com.HealthProTeach.User.Service.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperClass {
    @Bean
    public ModelMapper getMappers() {
        return new ModelMapper();
    }
}
