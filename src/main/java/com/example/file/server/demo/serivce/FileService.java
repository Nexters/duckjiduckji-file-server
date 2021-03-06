package com.example.file.server.demo.serivce;

import com.example.file.server.demo.dto.FileDto;
import com.example.file.server.demo.exception.FileUploadFailedException;
import com.example.file.server.demo.util.ApiHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ApiHelper apiHelper;

    public String uploadImg(FileDto fileDto) {

        int imgType = fileDto.getImgType();

        // 해당 유저에 대한 디렉토리 없으면 생성해줌.
        String imgDirPath = null;
        String imgPath = null;

        if(imgType == 0) { // 프로필 이미지
            final String fileName = "profileImg.png";
            imgDirPath = fileSavePath + "profile/" + fileDto.getUserId();
            imgPath = imgDirPath + "/" + fileName;
            imgReqUrl = contextPath + "/profile/" + fileDto.getUserId() + "/" + fileName;
        }
        else if(imgType == 1) { // 플로깅 이미지
            final String fileName = "plogging_" + apiHelper.makeNowTimeStamp() + ".png";
            imgDirPath = fileSavePath + "plogging/" + fileDto.getUserId();
            imgPath = imgDirPath + "/" + fileName;
            imgReqUrl = contextPath + "/plogging/" + fileDto.getUserId() + "/" + fileName;
        }

        File UserProfileFolder = new File(imgDirPath); // 유저 이름의 폴더 생성

        // 유저 폴더가 없을 경우 폴더 생성
        if(!UserProfileFolder.exists()) {
            try {
                UserProfileFolder.mkdir(); // 유저 id 폴더 생성
            } catch (Exception e) {
                log.info("[upload profile] Error : " + e.getMessage());
                throw new FileUploadFailedException();
            }
        }

        try {
            fileDto.getImg().transferTo(new File(imgPath));
            return imgReqUrl;
        } catch (IOException e) {
            log.info("[upload profile] Error : " + e.getMessage());
            throw new FileUploadFailedException();
        }
    };
}
