package com.example.TINFO370Project.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.TINFO370Project.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    @Transactional
    public Map<String, Object> upload(MultipartFile image){
        //Data Map to hand over to ck5 editor
        Map<String, Object> data = new HashMap<String, Object>();
        log.info("Image Upload Triggered::::::::::::::::::::::"+ image.getName());
        if(image != null){
            //save image through cloudinary
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "hanbfrtij",
                    "api_key", "823367694169484",
                    "api_secret", "TOpnr0bVx9XAOxcRHbJu3eea3dg",
                    "secure", true));

            // Change multipart to File format
            File targetFile = convert(image);

            try{
                Map uploadResult = cloudinary.uploader().upload(targetFile,ObjectUtils.emptyMap());
                log.info("cloudinary related:::::::::{}", uploadResult.entrySet().toString());

                data.put("uploaded",1);
                data.put("fileName",(String)uploadResult.get("secure_url"));
                data.put("url",(String) uploadResult.get("secure_url"));

                //Save image to ArticleImage


            }catch(IOException e){
                e.getMessage();
                log.info("Cloudinary Related");
            }

        }
        return data;
    }

    public File convert(MultipartFile file){
        File convFile = new File(file.getOriginalFilename());
        try{
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        }catch(IOException e){
            convFile = null;
        }
        return convFile;
    }
}
