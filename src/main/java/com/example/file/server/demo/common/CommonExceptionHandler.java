package com.example.file.server.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException e) {
        return new ResponseEntity<>(
                ApiResponse
                        .builder()
                        .code("500")
                        .msg(e.getMessage())
                        .body("")
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
