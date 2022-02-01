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

    /**
     *  이미지 업로드
     * @param fileDto
     * @return
     */
    @PostMapping("/img")
    public ResponseEntity<?> saveImg(FileDto fileDto) {

        log.info("[POST] /img " + fileDto.toString());

        if(fileDto.getRoomId() == null || fileDto.getContentId() == null || fileDto.getImg() == null) {
            throw new ParamInvalidException(FileConst.NOT_VALID_PARAMETER);
        }

        Map<String, String> obj = new HashMap<>();
        obj.put("img_url", fileService.uploadImg(fileDto));
        ApiResponse apiResponse = ApiResponse.builder()
                                  .msg(FileConst.SUCCESS_FILE_UPLOAD)
                                  .body(obj)
                                  .build();

        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/img")
    public ResponseEntity<?> deleteImg(@RequestParam(value = "roomId", required = false) String roomId,
                                       @RequestParam(value = "contentId", required = false) String contentId) {

        log.info("[DELETE] /img : " + "roomId : " + roomId + ", contentId : " + contentId);

        if(roomId == null || contentId == null) {
            throw new ParamInvalidException(FileConst.NOT_VALID_PARAMETER);
        }

        fileService.removeImg(roomId, contentId);
        ApiResponse apiResponse = ApiResponse.builder()
                                  .msg(FileConst.SUCCESS_FILE_REMOVE)
                                  .build();

        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }
}