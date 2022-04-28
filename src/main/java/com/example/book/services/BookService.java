package com.example.book.services;

import com.example.book.model.Book;
import com.example.book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class BookService {
    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> retrieveBooks(Map<String,String> allParams) {

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("price", contains().ignoreCase())
                .withMatcher("description", contains().ignoreCase());

        Book book = Book
                .builder()
                .name(allParams.get("name"))
                .price(allParams.get("price"))
                .description(allParams.get("description"))
                .build();

        return repository.findAll(Example.of(book, matcher));
    }


    public Optional<Book> retrieveBooks(String id) {
        return repository.findById(id);
    }

    public List<Book> retrieveBooksByName(String name) {
        return repository.findByName(name);
    }

    public List<Book> retrieveBooksByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public Book createBook(Book Book) {
        return repository.save(Book);
    }

    public Optional<Book> updateBook(String id, Book Book) {
        Optional<Book> BookOpt = repository.findById(id);
        if(!BookOpt.isPresent()) {
            return BookOpt;
        }
        Book.setId(id);
        return Optional.of(repository.save(Book));
    }

    public boolean deleteBook(String id) {
        try {
            repository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}