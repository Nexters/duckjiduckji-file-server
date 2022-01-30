package com.example.file.server.demo.common;

import lombok.*;
import org.springframework.lang.Nullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    String code;
    String msg;
    @Nullable
    T body;
}
