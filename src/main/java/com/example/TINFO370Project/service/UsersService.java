package com.example.TINFO370Project.service;


import com.example.TINFO370Project.entity.Subscriber;
import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.entity.Role;
import com.example.TINFO370Project.entity.RegUsers;
import com.example.TINFO370Project.entity.enums.SubscribeType;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {

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


    public void subscribe(String email) {
        Subscriber sub = Subscriber.builder()
                .subType(SubscribeType.TYPE_SUBSCRIPTION)
                .emailAddr(email)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        userRepository.save(sub);
        log.info("subscribtion info saved");
    }

    public Users delete(String id) {
       Users target_users =  userRepository.findById(Long.parseLong(id)).orElse(null);
       if(target_users!=null){
           userRepository.delete(target_users);
           return target_users;
       }
        else
            return null;
    }

    public Users update(String email, String subType) {
        log.info(email+"//////////////////////////"+subType);
        Users user = userRepository.findByEmail(email);

        if(user instanceof Subscriber){
            if(subType=="1"){
                ((Subscriber) user).setSubType(SubscribeType.TYPE_SUBSCRIPTION);
            }else
                ((Subscriber) user).setSubType(SubscribeType.TYPE_DONOR);
        }
        user.setEmailAddr(email);

    return userRepository.save(user);
    }
}
