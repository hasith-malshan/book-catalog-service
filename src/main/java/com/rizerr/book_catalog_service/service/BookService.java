package com.rizerr.book_catalog_service.service;

import com.rizerr.book_catalog_service.dao.BookDao;
import com.rizerr.book_catalog_service.entity.Author;
import com.rizerr.book_catalog_service.entity.Book;
import com.rizerr.book_catalog_service.repository.AuthorRepository;
import com.rizerr.book_catalog_service.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(Long isbn) {
        boolean isBookAvailable = findBookByIsbn(isbn).isPresent();
        if (isBookAvailable)
            return findBookByIsbn(isbn).get();
        else
            throw new IllegalStateException("there are no such book");
    }

    public List<Book> getBooksByTitle(String name) {
        return bookRepository.findByTitleStartingWith(name).stream().toList();
    }

    public Book addNewBook(BookDao bookDao) {

        if(findBookByIsbn(bookDao.getIsbn()).isPresent()){
            throw new IllegalStateException("You cannot add the same book Again");
        }

        Author bookAuthor = getAuthor(bookDao);

        Book book = new Book(
                bookDao.getIsbn(),
                bookDao.getTitle(),
                bookDao.getPrice(),
                bookDao.getQuantity(),
                bookAuthor,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Set<Book> booksOfAuthor = new HashSet<>();
        booksOfAuthor.add(book);

        bookAuthor.setBooks(booksOfAuthor);
        authorRepository.save(bookAuthor);

        return bookRepository.save(book);
    }

    private Author getAuthor(BookDao bookDao) {
        boolean isAuthorAvailable = getAuthorByName(bookDao.getAuthorName()).isPresent();

        if (isAuthorAvailable)
            return getAuthorByName(bookDao.getAuthorName()).get();
        else {
            return authorRepository.save(new Author(bookDao.getAuthorName(), LocalDateTime.now()));
        }

    }

    private Optional<Author> getAuthorByName(String author) {
        return authorRepository.findByName(author);
    }

    public void deleteBook(Long isbn) {
        boolean isBookPresent = findBookByIsbn(isbn).isPresent();
        if (isBookPresent)
            bookRepository.delete(findBookByIsbn(isbn).get());
        else
            throw new IllegalStateException("There is no Such book.");
    }

    public Book updateBook(BookDao bookDao) {
        boolean isBookPresent = findBookByIsbn(bookDao.getIsbn()).isPresent();
        System.out.printf(Boolean.toString(isBookPresent));
        if (isBookPresent){
            Book bookTobeUpdated =findBookByIsbn(bookDao.getIsbn()).get();
            bookTobeUpdated.setTitle(bookDao.getTitle());
            bookTobeUpdated.setAuthor(getAuthor(bookDao));
            bookTobeUpdated.setUpdatedAt(LocalDateTime.now());
            return bookRepository.save(bookTobeUpdated);
        }else {
            throw new IllegalStateException("Book Not Found");
        }

    }

    private Optional<Book> findBookByIsbn(Long isbn) {
        return bookRepository.findByIsbn(isbn);
    }


}
