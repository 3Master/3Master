package com.threemaster.websocket;

import java.io.IOException;

import org.springframework.web.socket.WebSocketSession;

import com.threemaster.entity.Message;


public interface WebsocketService {

    void addWebsocketSession(WebSocketSession webSocketSession);
    
    void removeWebsocketSession(WebSocketSession webSocketSession);
    
    void send(Message message) throws IOException;

}
