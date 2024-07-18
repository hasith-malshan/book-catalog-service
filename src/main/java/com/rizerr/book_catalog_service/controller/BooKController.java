package com.rizerr.book_catalog_service.controller;

import com.rizerr.book_catalog_service.dao.ApiResponse;
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
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("All Books");
        apiResponse.setContent(allBooks);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getSingleBookByIsbn(@PathVariable String isbn){
        Book bookFoundUsingIsbn = bookService.getBookByIsbn(Long.parseLong(isbn));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Requested single Book using :" + bookFoundUsingIsbn.getIsbn());
        apiResponse.setContent(bookFoundUsingIsbn);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getBooksByName(@PathVariable String name){
        List<Book> bookByTile = bookService.getBooksByTitle(name);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Books by Title : " + name);
        apiResponse.setContent(bookByTile);
        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookDao bookDao){
        Book updatedBook = bookService.updateBook(bookDao);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Book with isbn"+updatedBook.getIsbn()+" Updated.");
        apiResponse.setContent(updatedBook);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDao bookDao){
        Book newlyAddedBook = bookService.addNewBook(bookDao);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("New Book added to Database");
        apiResponse.setContent(newlyAddedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn){
        bookService.deleteBook(Long.parseLong(isbn));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Book Deleted");
        apiResponse.setContent("Book Deleted with isbn" + isbn);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
