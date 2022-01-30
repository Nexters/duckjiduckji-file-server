package com.example.file.server.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.Nullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    String code;
    String msg;
    T body;
}
