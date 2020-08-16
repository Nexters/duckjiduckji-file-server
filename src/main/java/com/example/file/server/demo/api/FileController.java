package com.example.file.server.demo.api;

import com.example.file.server.demo.FileConst;
import com.example.file.server.demo.common.ApiResponse;
import com.example.file.server.demo.dto.FileDto;
import com.example.file.server.demo.exception.FileUploadFailedException;
import com.example.file.server.demo.serivce.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/upload")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ApiResponse apiResponse;

    @PostMapping("/profileImg")
    public ResponseEntity<?> saveProfileImg(FileDto fileDto) {

        log.info("[POST] /upload/img " + fileDto.toString());

        apiResponse.setMsg(FileConst.SUCCESS_FILE_UPLOAD);
        Map<String, String> obj = new HashMap<>();
        obj.put("profile_url", fileService.uploadUserProfile(fileDto));
        apiResponse.setBody(obj);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/boardImg")
    public void saveBoardImg( ) {

    }
}
