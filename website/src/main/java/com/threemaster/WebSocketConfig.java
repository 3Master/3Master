//package com.threemaster;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
//    @Value("${stomp.port}")
//    int stompPort;
//
//    @Bean
//    public BrokerService brokerService() throws Exception {
//        return BrokerFactory.createBroker(String.format("broker:(vm://localhost,stomp://localhost:%d)" +
//                "?persistent=false&useJmx=false&useShutdownHook=true", stompPort));
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/portfolio").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
////        registry.enableSimpleBroker("/queue/", "/topic/");
//        registry.enableStompBrokerRelay("/queue/", "/topic/").setRelayPort(stompPort);
//    }
//}
