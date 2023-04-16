package com.example.TINFO370Project.api;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.TINFO370Project.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImageApiController {

    private final ImageService imageService;
    @PostMapping("/api/image")
    public Map<String,Object> imageUpload(@RequestParam("upload")MultipartFile image){
        //Data Map to hand over to ck5 editor
        Map<String, Object> data = imageService.upload(image);
        log.info("Image Upload Triggered::::::::::::::::::::::"+ image.getName());

        return data;
    }

}
