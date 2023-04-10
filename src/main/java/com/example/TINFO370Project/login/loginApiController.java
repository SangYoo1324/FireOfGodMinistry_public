package com.example.TINFO370Project.login;


import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.repository.UsersRepository;
import com.example.TINFO370Project.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class loginApiController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @PostMapping("/api/login")
    public String jwtTest(@RequestBody Map<String,String> loginForm){
       log.info(loginForm.get("username"));

        return "Login success";
    }

    @PostMapping("/api/join")
    public String join(@RequestBody Users users) throws Exception {
        usersService.signUp(users);
        return "joined";
    }

    @GetMapping("/api/user")
    public String user(){
        return "user";
    }

    @GetMapping("/api/admin")
    public String admin(){
        return "admin";
    }

}
