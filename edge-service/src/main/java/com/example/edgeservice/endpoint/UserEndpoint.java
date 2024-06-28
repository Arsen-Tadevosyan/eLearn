package com.example.edgeservice.endpoint;

import com.example.edgeservice.dto.UserRegisterDto;
import com.example.edgeservice.dto.UserResponseDto;
import com.example.edgeservice.dto.VerificationDto;
import com.example.edgeservice.dto.VerificationResponseDto;
import com.example.edgeservice.entity.User;
import com.example.edgeservice.exception.UserAlreadyExistException;
import com.example.edgeservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserEndpoint {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRegisterDto userRegisterDto,
                                                    @RequestParam(value = "picture", required = false) MultipartFile multipartFile) throws IOException {
        User byEmail = userService.findByEmail(userRegisterDto.getEmail());
        if (byEmail != null) {
            throw new UserAlreadyExistException();
        }
        UserResponseDto userResponseDto = userService.register(userRegisterDto, multipartFile);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping("/verification")
    public ResponseEntity<VerificationResponseDto> verification(@Valid @RequestBody VerificationDto verificationDto) {
        VerificationResponseDto responseDto = userService.verification(verificationDto);
        return ResponseEntity.ok(responseDto);
    }

}
