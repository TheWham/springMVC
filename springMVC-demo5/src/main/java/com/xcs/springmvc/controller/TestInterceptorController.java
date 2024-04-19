package com.xcs.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestInterceptorController {
    @RequestMapping("/testInterceptor")
    public String success(){
        return "success";
    }
    @RequestMapping("/testExceptionHandler")
    public String testExceptionHandler()
    {
        int a = 1 / 0;
        return "success";
    }
}
