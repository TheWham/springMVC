package com.xcs.springMVC.Controller;

import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class GetRequestParam {
    @RequestMapping("/testServletRequestParamAPI")
    public String testRequestParamAPI(HttpServletRequest servletRequest){
        HttpSession session = servletRequest.getSession();
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        System.out.println("username: " + username + ", password : " + password);
        return "success";
    }
    @RequestMapping("/testRequestParamInSpringMVC")
    public String testRequestParamInSpringMVC(
           @RequestParam(value = "user_name",required = false,defaultValue = "defaultXCSNB") String username,
            String password
    )
    {
        System.out.println("username: " + username + ", password : " + password);
        return "success";
    }
    @RequestMapping("/testGetFormParam")
    public String testGetFormParam(
        String username, String password, String[] hobby,
        @RequestHeader("HOST") String headerHost,
        @CookieValue("JSESSIONID") String JSESSIONID
    )
    {
        System.out.println("username : " + username + ", password : " + password + ", hobby : " + Arrays.toString(hobby));
        System.out.println("host is : " + headerHost);
        System.out.println("JSESSIONID is : " + JSESSIONID);
        return "success";
    }

}
