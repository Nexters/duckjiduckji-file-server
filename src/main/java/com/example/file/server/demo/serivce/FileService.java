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

        final String fileName = doAppendString(new String[]{"polaroid_", apiHelper.makeNowTimeStamp(), ".png"});
        String roomId = fileDto.getRoomId();

        createUploadDictory(roomId);

        try {
            String imgPath = doAppendString(new String[]{fileSavePath, "/", roomId, "/", fileName});
            log.info("imgPath : " + imgPath);
            fileDto.getImg().transferTo(new File(imgPath));
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }

        return doAppendString(new String[]{contextPath, "/", roomId, "/", fileName});
    };

    public void removeImg(String roomId, String fileName) {
        try {
            if(fileName == null) { // 방에 있는 모든 파일 삭제
                FileUtils.deleteDirectory(new File(doAppendString(new String[]{fileSavePath, "/", roomId})));
            } else { // 해당 파일만 삭제
                File file = new File(doAppendString(new String[]{fileSavePath, "/", roomId, "/", fileName}));
                boolean delete = file.delete();
                if(!delete) throw new FileRemoveFailedException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileRemoveFailedException();
        }
    }

    private void createUploadDictory(String roomId) {
        List<String> directoryList = new ArrayList<>();
        directoryList.add(roomId);

        createDirectoryRecursive(0, directoryList, fileSavePath);
    }

    private void createDirectoryRecursive(int d, List<String> directoryList, String imgDirPath) {

        if(d == directoryList.size()) return;

        String directoryName = directoryList.get(d);
        String imgPath = doAppendString(new String[]{imgDirPath, "/", directoryName});
        File ContentImageDirectory = new File(imgPath); // 컨텐츠 이미지 폴더 생성

        log.info("ContentImageDir : " + ContentImageDirectory);
        // 폴더가 없을 경우 폴더 생성
        if(!ContentImageDirectory.exists()) {
            try {
                boolean mkdir = ContentImageDirectory.mkdir();
                if(!mkdir) throw new FileUploadFailedException();
            } catch (Exception e) {
                e.printStackTrace();
                throw new FileUploadFailedException();
            }
        }

        createDirectoryRecursive(d+1, directoryList, doAppendString(new String[]{imgDirPath, "/", directoryName}));
    }

    private String doAppendString(String[] strArr) {
        StringBuilder sb = new StringBuilder();

        for (String s : strArr) {
            sb.append(s);
        }
        return sb.toString();
    }
}