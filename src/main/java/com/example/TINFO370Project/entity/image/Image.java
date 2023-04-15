package com.example.TINFO370Project.entity.image;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name ="IMAGE_CATEGORY", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public abstract class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String fileName;

    protected String filePath;

    protected String cloudinaryUrl;

    protected String contentType;

    protected Long size;

    protected Timestamp registeredDate;

    public Image(Long id, String fileName, String filePath, String cloudinaryUrl, String contentType, Long size, Timestamp registeredDate) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.cloudinaryUrl = cloudinaryUrl;
        this.contentType = contentType;
        this.size = size;
        this.registeredDate = registeredDate;
    }
}
