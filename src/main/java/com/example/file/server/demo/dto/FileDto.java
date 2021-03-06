package com.example.file.server.demo.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileDto {
    private String userId;
    private MultipartFile img;
    private int imgType; // 0 : 프로필 이미지, 1 : 산책 이미지
}