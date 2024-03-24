package com.xcs.springMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Parameter;

@Controller
public class TestRequestController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(
            value = {"/test", "/testMultiMapping", "/testParamInRequest"},
            method = {RequestMethod.GET, RequestMethod.POST},
            params = {"username", "password"}
    )
    public String success(){
        return "success";
    }
    @RequestMapping("/param")
    public String getParamPage(){
        return "param";
    }
    @GetMapping("/testGetMapping")
    public String testGetMap(){
        return "success";
    }
    @RequestMapping("/test/{id}/{name}")
    public String testMap(@PathVariable("id")Integer id, @PathVariable("name")String name) {
        System.out.println("id :" + id + " name is " + name);
        return "success";
    }
    @RequestMapping(
        value ={"/testAnt/a?a", "/testAnt/a*a", "/testAnt/**"}
    )
    public String testAntPath(){
        return "success";
    }
}
