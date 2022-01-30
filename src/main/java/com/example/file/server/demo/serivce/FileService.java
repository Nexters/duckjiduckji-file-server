package com.example.file.server.demo.serivce;

import com.example.file.server.demo.dto.FileDto;
import com.example.file.server.demo.exception.FileRemoveFailedException;
import com.example.file.server.demo.exception.FileUploadFailedException;
import com.example.file.server.demo.util.ApiHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileService {

    @Value("${filePath}")
    private String fileSavePath;

    @Value("${contextPath}")
    private String contextPath;

    private ApiHelper apiHelper;

    public FileService(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    public String uploadImg(FileDto fileDto) {

        final String fileName = apiHelper.makeNowTimeStamp() + ".png";
        String roomId = fileDto.getRoomId();
        String contentId = fileDto.getContentId();

        createUploadDictory(roomId, contentId);

        try {
            String imgPath = doAppendString(new String[]{fileSavePath, "/", roomId, "/", contentId, "/", fileName});
            fileDto.getImg().transferTo(new File(imgPath));
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }

        String imgReqUrl = doAppendString(new String[]{contextPath, "/", roomId, "/", contentId, "/", fileName});
        return imgReqUrl;
    };

    public void removeImg(String roomId, String contentId) {
        try {
            FileUtils.deleteDirectory(new File(doAppendString(new String[]{fileSavePath, "/", roomId, "/", contentId}))); // 맨 끝 디렉토리 기준 하위 디렉토리 모두 삭제
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileRemoveFailedException();
        }
    }

    void createUploadDictory(String roomId, String contentId) {
        List<String> directoryList = new ArrayList<>();
        directoryList.add(roomId);
        directoryList.add(contentId);

        createDirectoryRecursive(0, directoryList, fileSavePath);
    }

    void createDirectoryRecursive(int d, List<String> directoryList, String imgDirPath) {

        if(d == directoryList.size()) return;

        String directoryName = directoryList.get(d);
        String imgPath = doAppendString(new String[]{imgDirPath, "/", directoryName});
        File ContentImageDirectory = new File(imgPath); // 컨텐츠 이미지 폴더 생성

        // 폴더가 없을 경우 폴더 생성
        if(!ContentImageDirectory.exists()) {
            try {
                ContentImageDirectory.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadFailedException();
            }
        }

        createDirectoryRecursive(d+1, directoryList, doAppendString(new String[]{imgDirPath, "/", directoryName}));
    }


    public String doAppendString(String[ ] strArr) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<strArr.length; i++) {
            sb.append(strArr[i]);
        }
        return sb.toString();
    }
}