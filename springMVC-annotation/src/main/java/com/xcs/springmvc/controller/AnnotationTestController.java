package com.xcs.springmvc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class AnnotationTestController {
    @RequestMapping("/testAnnotation")
    public String index(){
        return "index";
    }
    @RequestMapping("/fileDownLoad")
    public ResponseEntity<byte[]> fileDownload(HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String photo = servletContext.getRealPath("/static/img/m.png");
        InputStream inputStream = new FileInputStream(photo);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=m.png");
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        inputStream.close();
        return responseEntity;
    }

    @RequestMapping(value = "/filePost", method = RequestMethod.POST)
    public String filePost(MultipartFile photo, HttpSession session) throws IOException {
        String filename = photo.getOriginalFilename();
        ServletContext servletContext = session.getServletContext();

        String suffix = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String lastFileName = uuid + suffix;

        String dir = servletContext.getRealPath("photo");
        File file = new File(dir);
        if (!file.exists()){
            file.mkdir();
        }

        String s = lastFileName.replaceAll("-", "");
        String photoPath = dir + File.separator + s;

        photo.transferTo(new File(photoPath));
        return "success";
    }
}
