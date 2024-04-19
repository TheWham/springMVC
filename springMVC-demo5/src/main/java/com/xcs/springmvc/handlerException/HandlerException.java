package com.xcs.springmvc.handlerException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
    public String ExceptionHandler(Model model, Exception ex){
        model.addAttribute("ex", ex);
        return "error";
    }
}
