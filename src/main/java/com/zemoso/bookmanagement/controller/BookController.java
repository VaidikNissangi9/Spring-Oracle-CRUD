package com.zemoso.bookmanagement.controller;

import com.zemoso.bookmanagement.entity.Book;
import com.zemoso.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/list")
    public List<Book> getBooks() {
        List<Book> books = service.getBooks();
        return books;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) {
        Book book = service.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> postBook(@Validated @RequestBody Book book) {
        Book book1 = service.postBook(book);
        return ResponseEntity.ok(book1);
    }

    @PutMapping
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        service.updateBook(book);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        service.deleteBook(bookId);
        return ResponseEntity.ok("");
    }

}
