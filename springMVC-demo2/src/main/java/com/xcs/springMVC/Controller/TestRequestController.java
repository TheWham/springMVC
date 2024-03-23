package com.xcs.springMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestRequestController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(
            value = {"/test", "/testMultiMapping"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String success(){
        return "success";
    }
    @GetMapping("/testGetMapping")
    public String testGetMap(){
        return "success";
    }
}
