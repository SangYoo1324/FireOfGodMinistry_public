package com.example.TINFO370Project.service;


import com.example.TINFO370Project.entity.Role;
import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUp(Users users) throws Exception {



        Users user = Users.builder()
                .password(users.getPassword())
                .username(users.getUsername())
                .roles(Role.valueOf(Role.ROLE_USER.toString()))
                .build();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


}
