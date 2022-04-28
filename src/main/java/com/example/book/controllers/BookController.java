package com.example.book.controllers;

import com.example.book.model.Book;
import com.example.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // Get all book
    @GetMapping()
    public ResponseEntity<?> getBooks(@RequestParam Map<String,String> allParams) {
        List<Book> books = bookService.retrieveBooks(allParams);
        return ResponseEntity.ok(books);
    }

    // Get book
    @GetMapping("/read-book/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable String id) {
        Optional<Book> book = bookService.retrieveBooks(id);
        if(!book.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(book);
    }

    // Add book
    @PostMapping("/add-book")
    public ResponseEntity<?> postBooks(@RequestBody Book body) {
        Book book = bookService.createBook(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // Update book
    @PutMapping("/update-book/{id}")
    public ResponseEntity<?> putBooks(@PathVariable String id, @RequestBody Book body) {
        Optional<Book> book = bookService.updateBook(id, body);
        return ResponseEntity.ok(book);
    }

    // DELETE book
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Book> deleteBooks(@PathVariable String id) {
        if(!bookService.deleteBook(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}