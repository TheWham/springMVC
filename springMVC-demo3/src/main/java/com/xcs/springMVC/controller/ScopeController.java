package com.xcs.springMVC.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ScopeController {
    @RequestMapping("/")
    public String testIndex(){
        return "index";
    }
    @RequestMapping("/testServletApIScope")
    public String testServletAPIScope(HttpServletRequest servletRequest){
        servletRequest.setAttribute("testServletAPIScope", "hello, servletAPIScope");
        return "success";
    }
    @RequestMapping("/testModelAndViewScope")
    public ModelAndView modelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("modelAndViewScope", "hello, modelAndView");
        modelAndView.setViewName("success");
        return modelAndView;
    }
    @RequestMapping("/testModelScope")
    public String testModelScope(Model model){
        model.addAttribute("modelScope", "hello, this is from model");
        return "success";
    }
    @RequestMapping("/testModelMapScope")
    public String testModelMapScope(ModelMap modelMap){
        modelMap.addAttribute("modelMapScope", "hello, this is from modelMap");
        return "success";
    }
    @RequestMapping("/testMapScope")
    public String testMapScope(Map<String, Object> map){
        map.put("mapScope", "hello, this is from Map");
        return "success";
    }
}
