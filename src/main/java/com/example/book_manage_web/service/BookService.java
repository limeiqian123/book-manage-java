package com.example.book_manage_web.service;


import com.example.book_manage_web.dto.BookDto;

import java.util.Optional;
import java.util.List;

public interface BookService {
    Optional<BookDto> add(BookDto userDto);

    Optional<BookDto> query(Integer id);

    Optional<List<BookDto>> list();

    Integer delete(Integer id);

    Optional<BookDto> update(BookDto bookDto);
}
