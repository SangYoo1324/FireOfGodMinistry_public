package com.example.TINFO370Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/page/main")
    public String main(){
        return "page/main";
    }

}