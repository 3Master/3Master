package com.threemaster.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChartHandler extends TextWebSocketHandler {
    
    @Autowired
    private WebsocketService websocketService;
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        websocketService.addWebsocketSession(session);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        websocketService.removeWebsocketSession(session);
    }

}
