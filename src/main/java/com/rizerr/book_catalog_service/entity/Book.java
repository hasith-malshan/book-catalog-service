package com.rizerr.book_catalog_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "isbn")
    private Long isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Double price;
    @ManyToMany(mappedBy = "books")
    private Set<Author> author;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Book(Long isbn, String title, Double price, Set<Author> author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
