package com.example.TINFO370Project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name ="USERS_CATEGORY", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Users  implements Comparable<Users>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String emailAddr;

    protected Timestamp creationDate;

    public Users(Long id, String emailAddr, Timestamp creationDate) {
        this.id = id;
        this.emailAddr = emailAddr;
        this.creationDate = creationDate;
    }

    public  Users(){

    }
}
