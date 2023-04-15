package com.example.TINFO370Project.entity.image;

import com.example.TINFO370Project.entity.Article;
import com.example.TINFO370Project.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@DiscriminatorValue("ARTICLE_IMAGE_INDICATOR")
@NoArgsConstructor
public class EventPostImage extends Image{

    @ManyToOne
    @JoinColumn(name="article_id", referencedColumnName = "id")
    private Article article;
    @ManyToOne
    @JoinColumn(name="posted_user", referencedColumnName = "id")
    private Users users;

    @Builder
    public EventPostImage(Long id, String fileName, String filePath, String cloudinaryUrl, String contentType, Long size, Timestamp registeredDate, Article article, Users users) {
        super(id, fileName, filePath, cloudinaryUrl, contentType, size, registeredDate);
        this.article = article;
        this.users = users;
    }

}
