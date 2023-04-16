package com.example.TINFO370Project.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ArticleApiControllerTest {

    @Test
    void post() {
        String date = "2023-04-11";

        log.info(date+LocalDateTime.now().toString().substring(10));
        Timestamp timestamp = Timestamp.valueOf(date+" "+LocalDateTime.now().toString().substring(11));
        log.info(timestamp.toString());
    }
}