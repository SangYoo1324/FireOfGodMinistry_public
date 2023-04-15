package com.example.TINFO370Project.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Slf4j
@DiscriminatorValue("REGULAR_USER_INDICATOR")
@AllArgsConstructor
public class RegUsers extends Users{

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 32, columnDefinition = "varchar(255) default 'ROLE_GUEST'")
    private Role roles;
    private String jwtToken;
    private String provider;
    private String providerId;

    @Builder
    public RegUsers(Long id, String emailAddr, Timestamp creationDate, String username, String password,
                    Role roles, String jwtToken, String provider, String providerId) {
        super(id, emailAddr, creationDate);
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.jwtToken = jwtToken;
        this.provider = provider;
        this.providerId = providerId;
    }




    public List<String> getRoleList(){

        log.info("유저가 가지고 있는 롤::::::::::::::::::"+this.roles.toString());
        return Arrays.asList(this.roles.toString());
    }



    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }


    @Override
    public int compareTo(Users o) {
        return (int)(getId()-o.getId());
    }
}
