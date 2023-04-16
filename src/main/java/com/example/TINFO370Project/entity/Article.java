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

    @Column
    private String thumbnailUrl;

    @Builder

    public Article(Long id, String title, String subTitle, String body,
                   Timestamp date, Timestamp creation, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.body = body;
        this.date = date;
        this.creation = creation;
        this.thumbnailUrl = thumbnailUrl;
    }
}
