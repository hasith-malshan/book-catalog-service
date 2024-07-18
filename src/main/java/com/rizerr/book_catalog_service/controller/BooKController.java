package com.rizerr.book_catalog_service.controller;

import com.rizerr.book_catalog_service.dao.BookDao;
import com.rizerr.book_catalog_service.entity.Book;
import com.rizerr.book_catalog_service.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BooKController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        List<Book> allBooks =  bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getSingleBookByIsbn(@PathVariable String isbn){
        Book bookFoundUsingIsbn = bookService.getBookByIsbn(Long.parseLong(isbn));
        return ResponseEntity.ok(bookFoundUsingIsbn);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getBooksByName(@PathVariable String name){
        return ResponseEntity.ok(bookService.getBooksByTitle(name));
    }


    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookDao bookDao){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookDao));
    }

    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDao bookDao){
        return ResponseEntity.status(HttpStatus.CREATED).body( bookService.addNewBook(bookDao));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn){
        bookService.deleteBook(Long.parseLong(isbn));
        return ResponseEntity.status(HttpStatus.OK).body("Book Deleted");
    }
}
