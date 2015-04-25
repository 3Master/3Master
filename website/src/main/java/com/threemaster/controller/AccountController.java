package com.threemaster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.threemaster.entity.User;
import com.threemaster.repository.UserRepository;

@Controller
public class AccountController {
    
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(User user, HttpServletRequest request, Model model){
        try {
            user = userRepository.save(user);
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            return "redirect:/search";
        } catch (Exception e) {
            model.addAttribute("errorMsg", "用户已存在！");
            return "register";
        }
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(User user, HttpServletRequest request, Model model){
        User realUser = userRepository.findByUsername(user.getUsername());
        if(realUser == null){
            model.addAttribute("errorMsg", "用户不存在！");
            return "login";
        }
        if(!realUser.getPassword().equals(user.getPassword())){
            model.addAttribute("errorMsg", "密码错误！");
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", realUser);
        return "redirect:/search";
    }
}
