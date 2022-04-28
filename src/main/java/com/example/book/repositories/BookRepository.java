package com.example.book.repositories;

import com.example.book.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByName(String name);

    List<Book> findByNameContains(String name);

}