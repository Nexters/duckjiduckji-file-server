package com.example.file.server.demo.exception;

import com.example.file.server.demo.FileConst;

public class FileRemoveFailedException extends RuntimeException{
    public FileRemoveFailedException() {
        super(FileConst.FAILED_FILE_REMOVE);
    }
}
