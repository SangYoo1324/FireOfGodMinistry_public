package com.example.TINFO370Project.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Slf4j
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private Role roles;

    private String refreshToken;

    private String email;

    @Builder
    public Users(Long id, String username ,String password, Role roles, String refreshToken, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.refreshToken = refreshToken;
        this.email = email;
    }


    public List<String> getRoleList(){

        log.info("유저가 가지고 있는 롤::::::::::::::::::"+this.roles.toString());
        return Arrays.asList(this.roles.toString());
    }



    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
