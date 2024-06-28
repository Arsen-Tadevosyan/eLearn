package com.example.edgeservice.entity;

import com.example.edgeservice.entity.enums.Gender;
import com.example.edgeservice.entity.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private int rating;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private int token;

    private boolean active;

    private LocalDate age;

    private String phone;

    private String picName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
