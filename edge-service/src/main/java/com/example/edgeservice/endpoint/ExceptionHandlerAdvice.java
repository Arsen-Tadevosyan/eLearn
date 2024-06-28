package com.example.edgeservice.endpoint;

import com.example.edgeservice.dto.ExceptionResponseDto;
import com.example.edgeservice.exception.EmailMismatchException;
import com.example.edgeservice.exception.UserAlreadyExistException;
import com.example.edgeservice.exception.UserRoleNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDto> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        ExceptionResponseDto errorResponse = ExceptionResponseDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .description("The user you are trying to create already exists in the system.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionResponseDto> handleIOException(IOException ex) {
        ExceptionResponseDto errorResponse = ExceptionResponseDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .description("An I/O error occurred while processing your request.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserRoleNotSupportedException.class)
    public ResponseEntity<ExceptionResponseDto> handleUserRoleNotSupportedException(UserRoleNotSupportedException ex) {
        ExceptionResponseDto errorResponse = ExceptionResponseDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .description("The provided user role is not supported.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailMismatchException.class)
    public ResponseEntity<ExceptionResponseDto> handleEmailMismatchException(EmailMismatchException ex) {
        ExceptionResponseDto errorResponse = ExceptionResponseDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .description("Email mismatch during verification.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}