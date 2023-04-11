package com.example.TINFO370Project.repository;

import com.example.TINFO370Project.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);

    Optional<String> findByJwtToken(String jwtToken);
    Optional<Users> findByEmail(String email);
}
