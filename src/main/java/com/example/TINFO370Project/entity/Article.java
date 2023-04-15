package com.example.TINFO370Project.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;

    @Column
    private String subTitle;
    @Lob
    @Column
    private String body;

    @Column
    private Timestamp date;

    @Column
    private Timestamp creation;

    @Builder
    public Article(Long id, String title, String body, Timestamp creation) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.creation = creation;
    }
}
