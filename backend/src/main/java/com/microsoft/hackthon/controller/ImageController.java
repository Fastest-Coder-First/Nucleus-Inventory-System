package com.microsoft.hackthon.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microsoft.hackthon.dto.RequestImage;
import com.microsoft.hackthon.service.UploadService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ImageController {

    private final UploadService uploadService;

    @PostMapping("/save-image")
    public ResponseEntity<String> registerUserInTime(@RequestBody RequestImage requestImage,
            HttpServletRequest request) throws IOException
            {
        final var userId = requestImage.getUserId();
        // image data
        String data = requestImage.getData();
        String[] strings = data.split(",");
        // getting file extension or png default
        String extension = getExtension(strings);
        log.debug("file extension: {}", extension);
        
        uploadService.saveImage(fileName(userId, extension), strings[1]);
        return ResponseEntity.ok("image saved successfully");



    }

        private static String fileName(String name, String extension) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = dateFormat.format(new Date());
        return name + currentTimeStamp + "." + extension;
    }

    private static String getExtension(String[] strings) {
        String extension;
        switch (strings[0]) {// check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default:// should write cases for more images types
                extension = "jpg";
                break;
        }
        return extension;
    }

}
