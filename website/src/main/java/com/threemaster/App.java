package com.threemaster;

import java.nio.charset.StandardCharsets;
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
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.threemaster.filter.MethodFiletr;
import com.threemaster.websocket.ChartHandler;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@EnableWebSocket
public class App extends SpringBootServletInitializer implements WebSocketConfigurer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
    
    public static void main(String[] args) {
        System.out.print(StandardCharsets.UTF_8);
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MethodFiletr());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChartHandler(), "/chat")
            .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
    
}