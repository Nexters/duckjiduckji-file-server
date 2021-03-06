package com.example.file.server.demo.exception;

public class ParamInvalidException extends RuntimeException {
    public ParamInvalidException(String errorMsg) {
        super(errorMsg);
    }
}
