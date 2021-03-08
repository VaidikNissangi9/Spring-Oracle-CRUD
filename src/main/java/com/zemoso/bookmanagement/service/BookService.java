package com.zemoso.bookmanagement.service;

import com.zemoso.bookmanagement.entity.Book;
import com.zemoso.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public Book getBookById(String bookId) {
        return repository.getBookById(bookId);
    }

    public Book postBook(Book book) {
        return repository.postBook(book);
    }

    public void deleteBook(String bookId) {
        repository.deleteBook(bookId);
    }

    public void updateBook(Book book) {
        repository.updateBook(book);
    }

    public List<Book> getBooks() {
        return repository.getBooks();
    }
}
