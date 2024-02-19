package com.example.book_manage_web.mapper;


import com.example.book_manage_web.dto.BookDto;
import com.example.book_manage_web.model.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    Integer saveOne(Book book);

    Book queryById(Integer id);

    List<Book> listAll();

    Integer delete(Integer id);

    Integer update(BookDto bookDto);
}
