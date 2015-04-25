package com.threemaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.threemaster.entity.User;
import com.threemaster.repository.UserRepository;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "", method=RequestMethod.GET)
    public String home(){
        return "redirect:/register";
    }
    
    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/search", method=RequestMethod.GET)
    public String search(@PageableDefault(value = 15, sort = { "createdTime" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model){
        Page<User> userPage = userRepository.findAll(pageable);
        model.addAttribute("users", userPage.getContent());
        return "search";
    }

    @RequestMapping(value = "/message", method=RequestMethod.GET)
    public String message(){
        return "message";
    }
}
