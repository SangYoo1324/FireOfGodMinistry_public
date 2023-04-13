package com.example.TINFO370Project.service;


import com.example.TINFO370Project.entity.enums.Role;
import com.example.TINFO370Project.entity.RegUsers;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegUsersService {

    private final UsersRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signUp(RegUsers regUsers) throws Exception {


        RegUsers user = RegUsers.builder()
                .password(regUsers.getPassword())
                .username(regUsers.getUsername())
                .roles(Role.valueOf(Role.ROLE_USER.toString()))
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


}
