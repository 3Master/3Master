package com.threemaster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.threemaster.entity.User;
import com.threemaster.repository.UserRepository;
import com.threemaster.util.HttpUtils;

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
            model.addAttribute("errorMsg", "请确认已存在！");
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

    @RequestMapping(value="/users/update", method=RequestMethod.GET)
    public String showUpdateSkills(HttpServletRequest request, Model model){
        User currentUser = HttpUtils.loginRequired(request);
        model.addAttribute("currentUser", currentUser);
        return "update";
    }
    
    @RequestMapping(value="/users/update", method=RequestMethod.PUT)
    public String updateSkills(@RequestParam String skill1, @RequestParam String skill2, 
            @RequestParam String skill3, HttpServletRequest request, Model model){
        User currentUser = HttpUtils.loginRequired(request);
        currentUser.setSkill1(skill1);
        currentUser.setSkill2(skill2);
        currentUser.setSkill3(skill3);
        userRepository.save(currentUser);
        model.addAttribute("currentUser", currentUser);
        return "update";
    }
}
