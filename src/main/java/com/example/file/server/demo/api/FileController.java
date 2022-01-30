package com.example.file.server.demo.api;

import com.example.file.server.demo.FileConst;
import com.example.file.server.demo.common.ApiResponse;
import com.example.file.server.demo.dto.FileDto;
import com.example.file.server.demo.exception.FileUploadFailedException;
import com.example.file.server.demo.exception.ParamInvalidException;
import com.example.file.server.demo.serivce.FileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final ApiResponse apiResponse;

    /*
    public FileController(FileService fileService, ApiResponse apiResponse) {
        this.fileService = fileService;
        this.apiResponse = apiResponse;
    }
    */
    /**
     *  이미지 업로드
     * @param fileDto
     * @return
     */
    @PostMapping("/upload/img")
    public ResponseEntity<?> saveImg(FileDto fileDto) {

        log.info(fileDto.toString());
        if(fileDto.getRoomId() == null || fileDto.getContentId() == null || fileDto.getImg() == null) {
            throw new ParamInvalidException(FileConst.NOT_VALID_PARAMETER);
        }

        log.info("[POST] /upload/img " + fileDto.toString());

        Map<String, String> obj = new HashMap<>();
        obj.put("img_url", fileService.uploadImg(fileDto));
        apiResponse.setMsg(FileConst.SUCCESS_FILE_UPLOAD);
        apiResponse.setBody(obj);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/img")
    public ResponseEntity<?> deleteImg(@RequestParam("roomId") String roomId,
                                       @RequestParam("contentId") String contentId) {

        if(roomId == null || contentId == null) {
            throw new ParamInvalidException(FileConst.NOT_VALID_PARAMETER);
        }

        fileService.removeImg(roomId, contentId);
        apiResponse.setMsg(FileConst.SUCCESS_FILE_REMOVE);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }
}
