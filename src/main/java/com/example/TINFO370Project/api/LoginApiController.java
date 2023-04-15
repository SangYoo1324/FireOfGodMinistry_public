package com.example.TINFO370Project.api;


import com.example.TINFO370Project.entity.RegUsers;
import com.example.TINFO370Project.entity.CustomUserDetails;
import com.example.TINFO370Project.service.CustomUserDetailsService;
import com.example.TINFO370Project.repository.UsersRepository;
import com.example.TINFO370Project.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginApiController {



    private final PasswordEncoder passwordEncoder;


    private final UsersRepository usersRepository;

    private final CustomUserDetailsService customUserDetailsService;

    private final UsersService usersService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<RegUsers> login(@RequestBody RegUsers loginForm, HttpServletRequest request){
       log.info(loginForm.getUsername());

       RegUsers fromDb= (RegUsers) usersRepository.findByUsername(loginForm.getUsername());//.orElse(null);

       //Manually Authenticate from regular login Form
        CustomUserDetails customUserDetails =(CustomUserDetails) customUserDetailsService.loadUserByUsername(loginForm.getUsername());
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(fromDb.getUsername(), fromDb.getPassword(), customUserDetails.getAuthorities());
        log.info("AuthenticationToken:  "+authenticationToken.getDetails());
        log.info(customUserDetails.getAuthorities().toString());

        // Authenticate AuthenticationToken
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("Login Status:  " +authentication.getPrincipal());
        // register Authentication to SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Update Session with securityContextholder.getContext
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());

        return ResponseEntity.status(HttpStatus.OK).body(fromDb);
    }

    @PostMapping("/api/join")
    public String join(@RequestBody RegUsers regUsers) throws Exception {
        usersService.signUp(regUsers);
        return "joined";
    }

    @GetMapping("/api/user")
    public String user(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userDetails =  authentication.getPrincipal();
        log.info("LogIn status::::::::::::"+userDetails.toString());
        log.info("Is Authenticated::::::::::::::"+authentication.isAuthenticated());
        return "user";
    }

    @GetMapping("/api/admin")
    public String admin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userDetails =  authentication.getPrincipal();
        log.info("LogIn status::::::::::::"+userDetails.toString());
        log.info("Is Authenticated::::::::::::::"+authentication.isAuthenticated());
        return "admin";
    }

}
