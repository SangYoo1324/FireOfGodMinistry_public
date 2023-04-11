package com.example.TINFO370Project.login;


import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.principal.PrincipalDetails;
import com.example.TINFO370Project.principal.PrincipalDetailsService;
import com.example.TINFO370Project.repository.UsersRepository;
import com.example.TINFO370Project.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@Slf4j
@RequiredArgsConstructor
public class loginApiController {



    private final PasswordEncoder passwordEncoder;


    private final UsersRepository usersRepository;

    private final PrincipalDetailsService principalDetailsService;

    private final UsersService usersService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<Users> login(@RequestBody Users loginForm, HttpServletRequest request){
       log.info(loginForm.getUsername());

       Users fromDb= usersRepository.findByUsername(loginForm.getUsername()).orElse(null);

       //Manually Authenticate from regular login Form
        PrincipalDetails principalDetails =(PrincipalDetails) principalDetailsService.loadUserByUsername(loginForm.getUsername());
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(fromDb.getUsername(), fromDb.getPassword(), principalDetails.getAuthorities());
        log.info("AuthenticationToken:  "+authenticationToken.getDetails());
        log.info(principalDetails.getAuthorities().toString());

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
    public String join(@RequestBody Users users) throws Exception {
        usersService.signUp(users);
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
