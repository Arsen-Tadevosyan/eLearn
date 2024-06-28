package com.example.edgeservice.dto;

import com.example.edgeservice.entity.enums.Gender;
import com.example.edgeservice.entity.enums.UserRole;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDto {

    private String name;

    private String surname;

    private String email;

    private int rating;

    private UserRole userRole;

    private boolean active;

    private LocalDate age;

    private String phone;

    private Gender gender;
}
