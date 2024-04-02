package com.xcs.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspViewResolverController {
    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
    @RequestMapping("/testJspView")
    public String testJspView(){
        return "success";
    }
}
