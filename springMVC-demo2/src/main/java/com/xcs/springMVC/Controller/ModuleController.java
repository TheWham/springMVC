package com.xcs.springMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class ModuleController {
    @RequestMapping("/testModuleRequest")
    public String testModuleRequest()
    {
        return "success";
    }
}
