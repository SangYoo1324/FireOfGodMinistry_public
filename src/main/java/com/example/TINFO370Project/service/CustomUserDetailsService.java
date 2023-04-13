package com.example.TINFO370Project.service;

import com.example.TINFO370Project.entity.CustomUserDetails;
import com.example.TINFO370Project.entity.RegUsers;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// when "/login" request come from AuthenticationManager
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    private final JwtTokenProviderService jwtTokenProviderService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            log.info("PrincipalDetialsService의 LoadByUsername() 실행됨");
        RegUsers regUsers = (RegUsers) usersRepository.findByUsername(username);//.orElse(null);
        log.info("LoadByUsername() 에서 추출한 usersInfo::::::"+ regUsers.toString());
        regUsers.setJwtToken(jwtTokenProviderService.jwtTokenCreator(regUsers.getUsername(), regUsers.getId()));
        usersRepository.save(regUsers);
        log.info("provided JWT Token::::::::::{}",regUsers.getJwtToken());
        return new CustomUserDetails(regUsers);
    }
}
