package com.example.book_manage_web.dto;

import lombok.Data;

@Data
public class BookDto {

    private Integer id;

    private String isbn;

    private String bookName;

    private String author;

    private Integer publishTime;

}
