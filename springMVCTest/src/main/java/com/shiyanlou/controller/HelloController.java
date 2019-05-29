package com.shiyanlou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(value="/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(){
        return "redirect:finalPage";
    }

    @RequestMapping(value = "/finalPage")
    public String finalPage(){
        return "final";
    }
}