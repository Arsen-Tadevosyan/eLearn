package com.example.edgeservice.dto;

import com.example.edgeservice.entity.enums.Gender;
import com.example.edgeservice.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "User role is mandatory")
    private UserRole userRole;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is mandatory")
    private LocalDate age;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
    private String phone;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;
}
