package com.reytech.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
    * This is a method say hello
    * @return message string
    */
    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    /**
    * @param model is a model map
    * @return message string
    */
    @GetMapping("/message")
    public String message(Model model) {
        model.addAttribute("message", "This is a custom message.");
        return "message";
    }
}
