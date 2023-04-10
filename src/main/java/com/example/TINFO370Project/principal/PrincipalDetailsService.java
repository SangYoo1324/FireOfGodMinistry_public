package com.example.TINFO370Project.principal;

import com.example.TINFO370Project.entity.Users;
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
public class PrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            log.info("PrincipalDetialsService의 LoadByUsername() 실행됨");
        Users users = usersRepository.findByUsername(username).orElse(null);
        return new PrincipalDetails(users);
    }
}
