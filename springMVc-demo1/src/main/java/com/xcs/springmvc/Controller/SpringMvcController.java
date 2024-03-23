package com.xcs.springmvc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringMvcController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/target")
    public String getTarget(){
        return "target";
    }
}
