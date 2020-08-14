package com.example.file.server.demo.common;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    String code;
    String msg;
    T body;
}
