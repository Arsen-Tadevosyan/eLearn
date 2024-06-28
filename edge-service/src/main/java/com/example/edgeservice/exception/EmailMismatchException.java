package com.example.edgeservice.exception;

public class EmailMismatchException extends RuntimeException{

    public EmailMismatchException() {
        super("Email mismatch");
    }
}
