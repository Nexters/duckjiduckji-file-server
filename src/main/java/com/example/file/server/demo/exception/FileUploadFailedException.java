package com.example.file.server.demo.exception;

import com.example.file.server.demo.FileConst;

import java.nio.channels.FileChannel;

public class FileUploadFailedException extends RuntimeException {
    public FileUploadFailedException() {
        super(FileConst.FAILED_FILE_UPLOAD);
    }
}
