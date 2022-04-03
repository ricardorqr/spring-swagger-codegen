package com.spring.swagger.codegen.controller;

import com.spring.swagger.codegen.api.BookApi;
import com.spring.swagger.codegen.model.BookRequest;
import com.spring.swagger.codegen.model.BookResponse;
import com.spring.swagger.codegen.model.BookResponseNoMessage;
import com.spring.swagger.codegen.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BookController implements BookApi {

    private BookService service;

    @Override
    public ResponseEntity<BookResponse> addBook(BookRequest bookRequest) {
        return service.saveBook(bookRequest);
    }

    @Override
    public ResponseEntity<BookResponse> deleteBook(Integer id) {
        return service.deleteBook(id);
    }

    @Override
    public ResponseEntity<List<BookResponseNoMessage>> getAllBooks() {
        return service.getAllBooks();
    }

    @Override
    public ResponseEntity<BookResponse> getBook(Integer id) {
        return service.getBook(id);
    }

    @Override
    public ResponseEntity<BookResponse> updateBook(Integer id, BookRequest bookRequest) {
        return service.updateBook(id, bookRequest);
    }

}
