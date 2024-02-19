package com.example.book_manage_web.model;

import lombok.Data;

@Data
public class Book {

    private Integer id;

    private String isbn;

    private String bookName;

    private String author;

    private Integer publishTime;
}
