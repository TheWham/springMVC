package com.xcs.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafViewController {
    @RequestMapping("/testForward")
    public String testForwardView(){
        return "forward:/testModelAndViewScope";
    }
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/testForwardAndRedirect";
    }
}
