package com.example.OlSoftwarePrueba.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private int status;
    private String message;
    private Object data;
    private LocalDateTime timestamp;

    // Constructor
    public ResponseDTO(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

}