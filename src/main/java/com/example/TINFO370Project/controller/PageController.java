package com.example.TINFO370Project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PageController {

    @GetMapping("/page/main")
    public String main(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userDetails =  authentication.getPrincipal();
        log.info("LogIn status::::::::::::"+userDetails.toString());
        log.info("Is Authenticated::::::::::::::"+authentication.isAuthenticated());
        return "page/main";
    }

    @GetMapping("/page/login")
    public String login(){
        return "page/login";

    }

    @GetMapping("/panel/admin")
    public String admin(){

        return "page/admin";
    }
    @GetMapping("/page/users")
    public String users(){

        return "page/users";
    }

    @GetMapping("/page/programservice")
    public String programService(){
        return "page/programService";
    }
    @GetMapping("/page/aboutus")
    public String aboutUs(){
        return "page/aboutUs";
    }
    @GetMapping("/page/donate")
    public String donate(){
        return "page/donate";
    }

    @GetMapping("/page/contact")
    public String contact(){
        return "page/contact";
    }
    @GetMapping("/page/contributors")
    public String contributors(){
        return "page/contributors";
    }
}
