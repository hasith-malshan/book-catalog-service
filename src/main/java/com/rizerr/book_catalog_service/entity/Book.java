package com.rizerr.book_catalog_service.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer isbn;
    private String title;
    private Double price;
    private Author Author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
