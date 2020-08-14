package com.example.file.server.demo.serivce;

import com.example.file.server.demo.dto.FileDto;
import com.example.file.server.demo.exception.FileUploadFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileService {

    @Value("${filePath}")
    private String fileSavePath;

    @Value("${contextPath}")
    private String contextPath;
    private String imgReqUrl;

    public String uploadUserProfile(FileDto fileDto) {

        // 해당 유저에 대한 디렉토리 없으면 생성해줌.
        String imgDirPath = fileSavePath + fileDto.getUserId();
        String imgPath = "";
        File UserProfileFolder = new File(imgDirPath);

        // 유저 폴더가 없을 경우 폴더 생성
        if(!UserProfileFolder.exists()) {
            try {
                UserProfileFolder.mkdir(); // 유저 id 폴더 생성
            } catch (Exception e) {
                log.info("[upload profile] Error : " + e.getMessage());
                throw new FileUploadFailedException();
            }
        }

        imgPath = imgDirPath + "/profileImg.png";
        imgReqUrl = contextPath + "/profile/" + fileDto.getUserId() + "/profileImg.png";
        try {
            fileDto.getProfileImg().transferTo(new File(imgPath));
            return imgReqUrl;
        } catch (IOException e) {
            log.info("[upload profile] Error : " + e.getMessage());
            throw new FileUploadFailedException();
        }
    };

    public void uploadBoardImage( ) {

    }
}
