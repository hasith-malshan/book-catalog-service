package com.rizerr.book_catalog_service.repository;

import com.rizerr.book_catalog_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByIsbn(Long isbn);
    Optional<Book> findByTitle(String name);

    Optional<Book> findByTitleStartingWith(String title);
}
