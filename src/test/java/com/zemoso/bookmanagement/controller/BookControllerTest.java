package com.zemoso.bookmanagement.controller;


import com.zemoso.bookmanagement.entity.Book;
import com.zemoso.bookmanagement.service.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerTest {

    private MockMvc mvc;

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @BeforeAll
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books/list"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBookById() throws Exception {
        Book book = new Book();
        book.setId("book1");
        when(bookService.getBookById(any())).thenReturn(book);
        mvc.perform(MockMvcRequestBuilders.get("/books/" + book.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{" +
                        "'id': 'book1'" + "}"));
    }

    @Test
    void postBook() throws Exception {
        String jsonString = "{\"id\": \"book2\", \"name\": \"star\", \"category\": \"sci-fi\", \"description\": \"good book\", \"author\": \"Jake\"} ";
        mvc.perform(MockMvcRequestBuilders.post("/books/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void updateBook() throws Exception {
        String jsonString="{\"id\": \"book2\", \"name\": \"thor\"} ";
        mvc.perform(MockMvcRequestBuilders.put("/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void deleteBook() {
        String bookId = "book1";
        ResponseEntity<String> result = bookController.deleteBook(bookId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
