package com.example.TINFO370Project.entity;

import com.example.TINFO370Project.entity.enums.SubscribeType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("SUBSCRIBER_INDICATOR")
public class Subscriber extends Users {
    @Enumerated(EnumType.STRING)
    private SubscribeType subType;

    @Builder
    public Subscriber(Long id, String emailAddr, Timestamp creationDate, SubscribeType subType) {
        super(id, emailAddr, creationDate);
        this.subType = subType;
    }

    public Subscriber(SubscribeType subType) {
        this.subType = subType;
    }

    @Override
    public int compareTo(Users o) {
        return (int) (getId()-o.getId());
    }
}
