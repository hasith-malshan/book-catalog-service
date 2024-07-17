package com.rizerr.book_catalog_service.controller;

import com.rizerr.book_catalog_service.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BooKController {

    @GetMapping
    public ResponseEntity getAllBooks(){
        return ResponseEntity.ok("Get All Books");
    }

    @GetMapping("/{isbn}")
    public ResponseEntity getSingleBookByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok("Book by Isbn");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getSingleBookByName(@PathVariable String name){
        return ResponseEntity.ok("Book by name");
    }


    @PutMapping("/{isbn}")
    public ResponseEntity updateBook(){
        return ResponseEntity.status(HttpStatus.OK).body(new Book());
    }

    @PostMapping
    public ResponseEntity addBook(){
        return ResponseEntity.status(HttpStatus.CREATED).body(new Book());
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity deleteBook(@PathVariable String isbn){
        return ResponseEntity.status(HttpStatus.OK).body("Book Deleted");
    }
}
