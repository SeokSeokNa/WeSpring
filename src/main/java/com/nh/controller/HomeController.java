package com.nh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getSignup(){
        return "main";
    }

    @RequestMapping("/tag")
    public String home2() {
        return "dynamic_tag";
    }
}

