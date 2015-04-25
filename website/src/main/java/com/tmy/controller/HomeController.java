package com.tmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @RequestMapping(value = "", method=RequestMethod.GET)
    public String home(){
        return "index";
    }
    
    @RequestMapping(value = "/test", method=RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "index";
    }

}
