package com.example.edgeservice.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("User already exists");
    }

}
