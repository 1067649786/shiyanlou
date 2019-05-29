package com.shiyanlou.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public Service service(){
        return new Service();
    }
    @Bean
    public Client client(){
        return new Client();
    }
}
