package com.example.file.server.demo;

import com.example.file.server.demo.api.FileController;
import com.example.file.server.demo.dto.FileDto;
import com.example.file.server.demo.exception.FileUploadFailedException;
import com.example.file.server.demo.serivce.FileService;
import com.example.file.server.demo.util.ApiHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class FileServerDemoApplicationTests {

    @Value("${filePath}")
    private String fileSavePath;

    @Autowired
    private ApiHelper apiHelper;

    @Autowired
    private FileService fileService;

    @Test
    @DisplayName("이미지 업로드 API")
    void imgUploadTest(){

        String roomId = "aaaaaa";
        File file = new File(fileSavePath + "/testImg.jpg");
        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile("aaa.jpg", new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileDto fileDto = FileDto.builder()
                            .roomId(roomId)
                            .img(multipartFile)
                            .build();

        String imgUrl = fileService.uploadImg(fileDto);
        log.info("imgUrl : " + imgUrl);
    }


    @Test
    @DisplayName("이미지 식제 API")
    void imgDeleteTest(){

        String roomId = "aaaaaa";
        String fileName = "polaroid_20220205160450.png"; // 폴라로이드 삭제
        //String fileName = null; // 방 삭제

        fileService.removeImg(roomId, fileName);
    }
}
