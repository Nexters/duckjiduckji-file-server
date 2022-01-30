package com.example.file.server.demo;

import com.example.file.server.demo.exception.FileUploadFailedException;
import com.example.file.server.demo.util.ApiHelper;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@SpringBootTest
class FileServerDemoApplicationTests {

    @Value("${filePath}")
    private String fileSavePath;

    @Autowired
    private ApiHelper apiHelper;

    @Test
    void contextLoads() {

    }

    @Test
    @DisplayName("create Folder")
    void createFolderTest(){

        final String fileName = apiHelper.makeNowTimeStamp() + ".png";
        String roomId = "aaaa";
        String contentId = "bbbb";
        String imgDirPath = fileSavePath;
        String imgPath = imgDirPath + "/" + fileName;

        List<String> folderList = new ArrayList<>();
        folderList.add(roomId);
        folderList.add(contentId);

        createFolder(0, folderList, imgDirPath);
    }

    void createFolder(int d, List<String> folderList, String imgDirPath) {

        if(d == folderList.size()) return;

        String folderName = folderList.get(d);
        imgDirPath += "/" + folderName;
        File ContentImageFolder = new File(imgDirPath); // 컨텐츠 이미지 폴더 생성

        // 유저 폴더가 없을 경우 폴더 생성
        if(!ContentImageFolder.exists()) {
            try {
                ContentImageFolder.mkdir(); // 유저 id 폴더 생성
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        createFolder(d+1, folderList, imgDirPath);
    }

    @Test
    @DisplayName("delete Folder")
    void deleteFolderTest( ) {
        try {
            FileUtils.deleteDirectory(new File(fileSavePath +"/aaaa")); // 맨 끝 디렉토리 기준 하위 디렉토리 모두 삭제
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
