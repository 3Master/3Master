package com.threemaster.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.threemaster.entity.User;
import com.threemaster.repository.UserRepository;

@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;
    
    @PostConstruct
    public void init(){
        User user = new User();
        for(Integer integer = 0; integer < 10; integer ++){
            user = new User();
            user.setEmail("test" + integer + "@test.com");
            user.setPassword(integer + "");
            user.setSkill1("ae");
            user.setSkill2("sdag");
            user.setSkill3("agsd");
            user.setUsername("aaa" + integer);
            userRepository.save(user);
        }
    }


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
            Model model, @RequestParam(value="skill", required=false, defaultValue="") String skill){
        if(skill.equals("")){
            Page<User> userPage = userRepository.findAll(pageable);
            model.addAttribute("users", userPage.getContent());
        }else {
            Page<User> userPage = userRepository.findBySkill1OrSkill2OrSkill3(skill, skill, skill, pageable);
            model.addAttribute("users", userPage.getContent());
        }
        return "search";
        
    }

    @RequestMapping(value = "/message", method=RequestMethod.GET)
    public String message(){
        return "message";
    }
}
