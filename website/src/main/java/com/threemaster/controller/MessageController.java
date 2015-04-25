package com.threemaster.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.threemaster.entity.Message;
import com.threemaster.entity.User;
import com.threemaster.repository.MessageRepository;
import com.threemaster.repository.UserRepository;
import com.threemaster.util.HttpUtils;
import com.threemaster.websocket.WebsocketService;

@RestController
public class MessageController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MessageRepository messageRepository;
    
    
    @Autowired
    private WebsocketService websocketService;
    @RequestMapping(value="chat/{toId}", method=RequestMethod.POST)
    public void addChat(@PathVariable Integer toId, HttpServletRequest request, String content) throws IOException{
        User current = HttpUtils.loginRequired(request);
//        User to = userRepository.findOne(toId);
        Message message = new Message();
        message.setContent(content);
        message.setFromId(current.getId());
        message.setToId(toId);
        message.setRead(false);
        messageRepository.save(message);
        websocketService.send(message);
    }
    
    @RequestMapping(value="chat/{toId}/messages", method=RequestMethod.GET)
    @ResponseBody
    public List<String> getMessages(@PathVariable Integer toId, HttpServletRequest request, String content) throws IOException{
        List<String> contents = Lists.newArrayList();
        User current = HttpUtils.loginRequired(request);
        User to = userRepository.findOne(toId);
        List<Message> messages =  messageRepository.findByFromIdAndToIdAndRead(to.getId(), current.getId(), false);
        for (Message message : messages) {
            message.setRead(true);
            contents.add(message.getContent());
        }
        messageRepository.save(messages);
        return contents;
    }

}
