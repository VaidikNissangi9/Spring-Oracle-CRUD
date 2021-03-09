package com.zemoso.bookmanagement.repository;

import com.zemoso.bookmanagement.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getBooks() {
        String query = "select * from books";
        List<Book> books = new ArrayList<>();
        return jdbcTemplate.query(query, resultSet -> {
            Book book = new Book();
            while (resultSet.next()) {
                book.setId(resultSet.getString("id"));
                book.setName(resultSet.getString("name"));
                book.setCategory(resultSet.getString("category"));
                book.setDescription(resultSet.getString("description"));
                book.setAuthor(resultSet.getString("author"));
                books.add(book);
            }
            return books;
        });
    }

    public Book getBookById(String bookId) {
        String query = "select * from books where id = ?";
        return jdbcTemplate.query(query, resultSet -> {
            Book book = new Book();
            while (resultSet.next()) {
                book.setId(bookId);
                book.setName(resultSet.getString("name"));
                book.setCategory(resultSet.getString("category"));
                book.setDescription(resultSet.getString("description"));
                book.setAuthor(resultSet.getString("author"));
            }
            return book;
        }, bookId);
    }


    public Book postBook(Book book) {
        String query = "insert into books (id, name, category, description, author) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, book.getId(), book.getName(), book.getCategory(), book.getDescription(), book.getAuthor());
        return book;
    }

    public void deleteBook(String bookId) {
        String query = "delete from books where id = ?";
        jdbcTemplate.update(query, bookId);
    }

    public void updateBook(Book book) {
        String query = "update books set description = ? where id = ?";
        jdbcTemplate.update(query, book.getDescription(), book.getId());
    }
}
