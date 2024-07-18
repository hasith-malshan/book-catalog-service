package com.rizerr.book_catalog_service.repository;

import com.rizerr.book_catalog_service.entity.Book;
import com.rizerr.book_catalog_service.entity.BookV2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepositoryV2 extends JpaRepository<BookV2, Integer> {
    Optional<Book> findByIsbn(Long isbn);
    Optional<Book> findByTitle(String name);
    Optional<Book> findByTitleStartingWith(String title);
}
