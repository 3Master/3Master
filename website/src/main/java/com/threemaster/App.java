package com.threemaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;

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
    @ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}