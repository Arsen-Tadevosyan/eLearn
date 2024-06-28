package com.example.edgeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationResponseDto {

    private String message;
    private UserResponseDto userResponseDto;
}
