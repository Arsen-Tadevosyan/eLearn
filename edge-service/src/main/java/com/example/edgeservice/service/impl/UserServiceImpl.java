package com.example.edgeservice.service.impl;

import com.example.edgeservice.dto.UserRegisterDto;
import com.example.edgeservice.dto.UserResponseDto;
import com.example.edgeservice.dto.VerificationDto;
import com.example.edgeservice.dto.VerificationResponseDto;
import com.example.edgeservice.entity.User;
import com.example.edgeservice.entity.enums.UserRole;
import com.example.edgeservice.exception.EmailMismatchException;
import com.example.edgeservice.exception.UserRoleNotSupportedException;
import com.example.edgeservice.mapper.UserMapper;
import com.example.edgeservice.repository.UserRepository;
import com.example.edgeservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SendMailService sendMailService;

    @Value("${picture.upload.directory}")
    private String uploadDirectory;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserResponseDto register(UserRegisterDto userRegisterDto, MultipartFile image) throws IOException {
        if (userRegisterDto.getUserRole() == UserRole.ADMIN) {
            throw new UserRoleNotSupportedException();
        }
        User user = userMapper.map(userRegisterDto);
        Random random = new Random();
        int randomNumber = random.nextInt(899999) + 100000;
        user.setToken(randomNumber);
        String picName = "";
        if (image != null && !image.isEmpty()) {
            picName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            image.transferTo(file);
        }
        user.setPicName(picName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        userRepository.save(user);
        sendMailService.send(user.getEmail(), "Verification", "it is your verification code -> " + user.getToken());
        return userMapper.map(user);
    }

    @Override
    public VerificationResponseDto verification(VerificationDto verificationDto) {
        Optional<User> user = userRepository.findByToken(verificationDto.getToken());
        if (user.isEmpty() || !user.get().getEmail().equals(verificationDto.getEmail())) {
            throw new EmailMismatchException();
        }
        user.get().setActive(true);
        user.get().setToken(0);
        userRepository.save(user.get());
        return VerificationResponseDto.builder()
                .message("Verification successful")
                .userResponseDto(userMapper.map(user.get()))
                .build();
    }
}
