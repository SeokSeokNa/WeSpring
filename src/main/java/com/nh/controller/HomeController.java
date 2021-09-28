package com.nh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "main";
    }

    @RequestMapping("/tag")
    public String home2() {
        return "dynamic_tag";
    }
}

