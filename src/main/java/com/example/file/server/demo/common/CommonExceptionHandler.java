package com.example.file.server.demo.common;

import com.example.file.server.demo.exception.ParamInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    // Server Error
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException e) {

        e.printStackTrace();
        return new ResponseEntity<>(
                ApiResponse
                        .builder()
                        .code("500")
                        .msg(e.getMessage())
                        .body(null)
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // parameter Error
    @ExceptionHandler({ParamInvalidException.class})
    public ResponseEntity<?> img400Exception(Exception e) {

        e.printStackTrace();
        return new ResponseEntity<>(
                ApiResponse
                        .builder()
                        .code("400")
                        .msg(e.getMessage())
                        .body(null)
                        .build()
                , HttpStatus.BAD_REQUEST);
    }
}
