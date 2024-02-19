package com.example.book_manage_web.service;

import com.example.book_manage_web.dto.BookDto;
import com.example.book_manage_web.mapper.BookMapper;
import com.example.book_manage_web.model.Book;
import com.example.book_manage_web.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Optional<BookDto> add(BookDto bookDto) {
        Book bookInput = new Book();
        BeanUtils.copyProperties(bookDto, bookInput);
        bookMapper.saveOne(bookInput);
        BookDto bookDtoRes = new BookDto();
        BeanUtils.copyProperties(bookInput, bookDtoRes);
        return Optional.of(bookDtoRes);
    }

    @Override
    public Optional<BookDto> query(Integer id) {

        Book bookRes = bookMapper.queryById(id);
        BookDto bookDtoRes = new BookDto();
        BeanUtils.copyProperties(bookRes, bookDtoRes);
        return Optional.of(bookDtoRes);
    }

    @Override
    public Optional<List<BookDto>> list() {

        List<Book> bookRes = bookMapper.listAll();
        List<BookDto> bookDtoListRes = new ArrayList<>();
        for (Book book : bookRes) {
            BookDto bookDto = new BookDto();
            BeanUtils.copyProperties(book, bookDto);
            bookDtoListRes.add(bookDto);
        }
        return Optional.of(bookDtoListRes);
    }

    @Override
    public Integer delete(Integer id) {
        return bookMapper.delete(id);
    }

    @Override
    public Optional<BookDto> update(BookDto bookDto) {

        Book book = bookMapper.queryById(bookDto.getId());
        Integer count = bookMapper.update(bookDto);
        BeanMap beanMap = BeanMap.create(book);
        if (count > 0) {
            BeanUtil.beanCopy(beanMap, bookDto);
        }
        Optional<BookDto> res = Optional.of(bookDto);
        return res;
    }

}
