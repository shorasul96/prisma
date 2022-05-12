package com.example.prisma.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseDTO {
    public CommonResponseDTO(String message, int responseCode) {
        this.data = null;
        this.message = message;
        this.responseCode = responseCode;
    }

    private Object data;
    private String message;
    private int responseCode;
}
