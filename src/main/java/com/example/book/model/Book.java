package com.example.book.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Document(value="books")
public class Book {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String price;
    @NotNull
    private String description;

}