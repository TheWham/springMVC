package com.xcs.springmvc.controller;

import com.xcs.springmvc.pojo.User;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Controller
public class RequestAndResponseMessageController {
    @RequestMapping(value = "/testRequestBody", method = RequestMethod.POST)
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println(requestBody);
        return "success";
    }
    @RequestMapping(value = "/testRequestEntity", method = RequestMethod.POST)
    public String testRequestBody(RequestEntity<String> requestEntity){
        HttpHeaders headers = requestEntity.getHeaders();
        String body = requestEntity.getBody();
        System.out.println(headers);
        System.out.println(body);
        return "success";
    }
    @RequestMapping(value = "/testResponseBody", method = RequestMethod.GET)
    @ResponseBody
    public String testResponseBody()
    {
        return "success";
    }
    @RequestMapping(value = "/testResponseObject", method = RequestMethod.GET)
    @ResponseBody
    public User testResponseObject()
    {
        return new User("许迟帅", "20", "231231", "男");
    }
    @RequestMapping(value = "/testAjax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String username, String password){
        System.out.println(username);
        System.out.println(password);
        return "hello,ajax";
    }
    @RequestMapping(value = "/testDownLoadFile", method = RequestMethod.GET)
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String photo = servletContext.getRealPath("/static/img/m.png");
        InputStream is = new FileInputStream(photo);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename=m.png");
        HttpStatus ok = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, ok);
        is.close();
        return responseEntity;
    }
    @RequestMapping(value = "/testPostFileToDir", method = RequestMethod.POST)
    public String testPostFileToDir(MultipartFile photo, HttpSession session){
        String originalFilename = photo.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String uuid = UUID.randomUUID().toString();
        ServletContext servletContext = session.getServletContext();
        String photo1 = servletContext.getRealPath("photo");
        File file = new File(photo1);

        if (!file.exists()){
            file.mkdir();
        }
        String filename = uuid + suffix;
        String s = filename.replaceAll("-", "");
        String finalFileName = photo1 + File.separator + s;

        try {
            photo.transferTo(new File(finalFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }
}
