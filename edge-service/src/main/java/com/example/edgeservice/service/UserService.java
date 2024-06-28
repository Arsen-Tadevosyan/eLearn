package com.example.edgeservice.service;

import com.example.edgeservice.dto.UserRegisterDto;
import com.example.edgeservice.dto.UserResponseDto;
import com.example.edgeservice.dto.VerificationDto;
import com.example.edgeservice.dto.VerificationResponseDto;
import com.example.edgeservice.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    User findByEmail(String email);

    UserResponseDto register(UserRegisterDto userRegisterDto, MultipartFile image) throws IOException;

    VerificationResponseDto verification(VerificationDto verificationDto);
}
