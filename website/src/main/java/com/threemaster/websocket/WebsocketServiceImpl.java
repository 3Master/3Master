package com.threemaster.websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.threemaster.entity.Message;
import com.threemaster.entity.User;
import com.threemaster.repository.MessageRepository;

@Service
public class WebsocketServiceImpl implements WebsocketService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    private final Map<Integer, List<WebSocketSession>> sessons = Maps.newHashMap();
    
    @Override
    public void send(Message message) throws IOException {
        List<WebSocketSession> sessions = sessons.get(message.getToId());
        if(sessions == null || sessions.isEmpty()){
            return;
        }
        message.setRead(true);
        messageRepository.save(message);
        for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(new TextMessage(message.getContent()));
        }
    }

    @Override
    public void addWebsocketSession(WebSocketSession webSocketSession) {
        User currnet = (User)webSocketSession.getAttributes().get("currentUser");
        if(sessons.containsKey(currnet.getId())){
            sessons.get(currnet.getId()).add(webSocketSession);
        }else {
            sessons.put(currnet.getId(), Lists.newArrayList(webSocketSession));
        }
    }

    @Override
    public void removeWebsocketSession(WebSocketSession webSocketSession) {
        User currnet = (User)webSocketSession.getAttributes().get("currentUser");
        sessons.get(currnet.getId()).remove(webSocketSession);
    }

}
