package com.example.TINFO370Project.service;


import com.example.TINFO370Project.entity.Role;
import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(Users users) throws Exception {



        Users user = Users.builder()
                .password(users.getPassword())
                .username(users.getUsername())
                .roles(Role.valueOf(Role.ROLE_ADMIN.toString()))
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }


}
