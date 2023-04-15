package com.example.TINFO370Project.controller;

import com.example.TINFO370Project.entity.RegUsers;
import com.example.TINFO370Project.entity.Subscriber;
import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PageController {

    private final UsersRepository usersRepository;


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
    public String admin(Model model){
        //userInfo manager
        List<Users> usersList = usersRepository.findAllUsers();

       List<RegUsers> regUsersList= usersList.stream().filter(users -> {return users instanceof RegUsers;
        }).map(u-> (RegUsers)u).collect(Collectors.toList());


        List<Subscriber> subscribersList= usersList.stream().filter(users -> {return users instanceof Subscriber;
        }).map(u-> (Subscriber)u).collect(Collectors.toList());

        log.info("Total Users size:::::::::"+usersList.size());
        model.addAttribute("usersList",regUsersList);
        model.addAttribute("subscribersList",subscribersList);

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
