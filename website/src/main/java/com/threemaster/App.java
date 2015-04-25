package com.threemaster;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.threemaster.filter.MethodFiletr;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class App extends SpringBootServletInitializer  {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MethodFiletr());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }
    
}