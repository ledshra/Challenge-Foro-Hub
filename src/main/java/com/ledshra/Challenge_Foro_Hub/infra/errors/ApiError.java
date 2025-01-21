package com.ledshra.Challenge_Foro_Hub.infra.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

class ApiError {
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, MethodArgumentNotValidException ex) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}