package com.xcs.springMVC.Controller;

import com.xcs.springMVC.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestPojoController {
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    @RequestMapping("/testpojo")
    public String testPojo(User user)
    {
        System.out.println(user);
        return "success";
    }
}
