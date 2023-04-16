package com.example.TINFO370Project.api;

import com.example.TINFO370Project.entity.Article;
import com.example.TINFO370Project.repository.ArticleRepository;
import com.example.TINFO370Project.service.ArticleService;
import com.example.TINFO370Project.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleApiController {

    private final ArticleService articleService;
    private final ImageService imageService;

    private final ArticleRepository articleRepository;

    @PostMapping("/api/article")
    public ResponseEntity<String> post(@RequestParam ("title") String title,
                                       @RequestParam ("subTitle") String subTitle,
                                       @RequestParam ("body") String body,
                                       @RequestParam ("date") String date,
                                       @RequestParam ("file") MultipartFile image){
        log.info(title);
        log.info(subTitle);
        log.info(date);

        Map<String, Object> data = imageService.upload(image);
        log.info("Cloudinary URL for Thumbnail {}",data.get("url").toString());

        Article article = Article.builder()
            .title(title)
                .subTitle(subTitle)
                .body(body)
                .date(Timestamp.valueOf(date+" "+LocalDateTime.now().toString().substring(11)))
                .thumbnailUrl(data.get("url").toString())
                .creation(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        articleRepository.save(article);

        return ResponseEntity.status(HttpStatus.OK).body("Article Successfully stored into DB");
    }
}
