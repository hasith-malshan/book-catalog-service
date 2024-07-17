package com.rizerr.book_catalog_service.repository;

import com.rizerr.book_catalog_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByIsbn(Integer integer);
    Optional<Book> findByName(String name);
}
