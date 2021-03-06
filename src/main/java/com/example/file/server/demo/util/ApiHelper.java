package com.example.file.server.demo.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ApiHelper {

    private static SimpleDateFormat time_format = new SimpleDateFormat ( "yyyyMMddHHmmss");

    public String makeNowTimeStamp( ) {
        Date date = new Date();
        String now = time_format.format(date);
        return now;
    }
}
