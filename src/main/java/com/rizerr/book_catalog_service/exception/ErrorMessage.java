package com.rizerr.book_catalog_service.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private String message;
    private LocalDateTime dateTime;
}
