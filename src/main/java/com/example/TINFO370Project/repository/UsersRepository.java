package com.example.TINFO370Project.repository;

import com.example.TINFO370Project.entity.RegUsers;
import com.example.TINFO370Project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    @Query(value = "select *" +
            " from users left join regUsers on users.id = regUsers.id " +
            "left join subscriber on users.id = subscriber.id " +
            "where username=?1", nativeQuery = true)
    Users findByUsername(String username);

//    Users findByJwtToken(String jwtToken);
    @Query(value = "select *" +
        " from users left join regUsers on users.id = regUsers.id " +
        "left join subscriber on users.id = subscriber.id " +
        "where emailAddr=?1", nativeQuery = true)
    Users findByEmail(String email);

    @Query(value = "select users.id,users.users_category,users.creationDate," +
            "regUsers.username,regUsers.password,regUsers.jwttoken,regUsers.providerid,"+
            " COALESCE(subscriber.subType, 'TYPE_REGULAR') as subType"+
            ",COALESCE(users.emailaddr,'No Email Provided') as emailAddr,COALESCE(regUsers.roles,'ROLE_GUEST') as Roles," +
            "COALESCE(regUsers.provider,'Non-SocialLogin') as provider"+
            " from users left join regUsers on users.id = regUsers.id " +
            "left join subscriber on users.id = subscriber.id "+
            "order by users.id;"
            , nativeQuery = true)
    List<Users> findAllUsers();

}
