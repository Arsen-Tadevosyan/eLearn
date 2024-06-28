package com.example.edgeservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class VerificationDto {

    @NotNull
    @NumberFormat
    @Min(value = 100000, message = "not valid token")
    @Max(value = 999999, message = "not valid token")
    private int token;

    @NotBlank
    @Email
    private String email;

}
