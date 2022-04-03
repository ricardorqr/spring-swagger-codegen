package com.spring.swagger.codegen.service;

import com.spring.swagger.codegen.model.Book;
import com.spring.swagger.codegen.model.BookRequest;
import com.spring.swagger.codegen.model.BookResponse;
import com.spring.swagger.codegen.model.BookResponseNoMessage;
import com.spring.swagger.codegen.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository repository;

    public ResponseEntity<BookResponse> saveBook(BookRequest bookRequest) {
        Book book = Book.builder()
                        .author(bookRequest.getAuthor())
                        .title(bookRequest.getTitle())
                        .build();
        repository.save(book);
        return new ResponseEntity<>(changeBookToBookResponse(book, "Book inserted successfully"), HttpStatus.OK);
    }

    public ResponseEntity<BookResponse> deleteBook(Integer id) {
        Book book = repository.findById(id)
                              .orElse(null);
        if (book != null) {
            repository.deleteById(id);
            return new ResponseEntity<>(changeBookToBookResponse(book, "Book deleted successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(changeBookToBookResponse(new Book(), "Book not found"), HttpStatus.OK);
        }
    }

    public ResponseEntity<BookResponse> getBook(Integer id) {
        Book book = repository.findById(id)
                              .orElse(null);
        if (book != null) {
            return new ResponseEntity<>(changeBookToBookResponse(book, "Book found successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(changeBookToBookResponse(new Book(), "Book not found"), HttpStatus.OK);
        }
    }

    public ResponseEntity<BookResponse> updateBook(Integer id, BookRequest bookRequest) {
        Book book = repository.findById(id)
                              .orElse(null);
        if (book != null) {
            book.setTitle(bookRequest.getTitle());
            book.setAuthor(bookRequest.getAuthor());
            repository.saveAndFlush(book);
            return new ResponseEntity<>(changeBookToBookResponse(book, "Book updated successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(changeBookToBookResponse(new Book(), "Book not found"), HttpStatus.OK);
        }
    }

    private BookResponseNoMessage changeBookToBookResponseNoMessage(Book book) {
        BookResponseNoMessage response = new BookResponseNoMessage();
        response.setAuthor(book.getAuthor());
        response.setTitle(book.getTitle());
        return response;
    }

    private BookResponse changeBookToBookResponse(Book book, String message) {
        BookResponse response = new BookResponse();
        response.setAuthor(book.getAuthor());
        response.setTitle(book.getTitle());
        response.setMessage(message);
        return response;
    }

    public ResponseEntity<List<BookResponseNoMessage>> getAllBooks() {
        List<Book> books = repository.findAll();
        List<BookResponseNoMessage> bookResponses = books.stream()
                                                .map(this::changeBookToBookResponseNoMessage)
                                                .collect(Collectors.toList());
        return new ResponseEntity<>(bookResponses, HttpStatus.OK);
    }

}
