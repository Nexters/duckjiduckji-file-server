package com.example.file.server.demo.api;

import com.example.file.server.demo.FileConst;
import com.example.file.server.demo.common.ApiResponse;
import com.example.file.server.demo.dto.FileDto;
import com.example.file.server.demo.exception.FileUploadFailedException;
import com.example.file.server.demo.exception.ParamInvalidException;
import com.example.file.server.demo.serivce.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  이미지 업로드
     * @param fileDto
     * @return
     */
    @PostMapping("/img")
    public ResponseEntity<?> saveProfileImg(FileDto fileDto) {

        if(fileDto.getImg() == null || fileDto.getUserId() == null || fileDto.getImgType() == null) {
            throw new ParamInvalidException(FileConst.NOT_VALID_PARAMETER);
        }

        log.info("[POST] /upload/img " + fileDto.toString());

        Map<String, String> obj = new HashMap<>();
        obj.put("img_url", fileService.uploadImg(fileDto));
        apiResponse.setMsg(FileConst.SUCCESS_FILE_UPLOAD);
        apiResponse.setBody(obj);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }
}
