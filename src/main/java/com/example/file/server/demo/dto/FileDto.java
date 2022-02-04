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
    private String roomId;
    private MultipartFile img;
}