package com.example.book_manage_web.service;

import com.example.book_manage_web.dto.BookDto;
import com.example.book_manage_web.mapper.BookMapper;
import com.example.book_manage_web.entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private IBookService IBookService;

    @MockBean
    private BookMapper bookMapper;

    @Test
    public void testBookAdd() {
        Book bookReturn = new Book();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);

        Mockito.when(bookMapper.saveOne(Mockito.any(Book.class))).thenReturn(1);

        BookDto bookInput = new BookDto();
        bookInput.setIsbn("9787011334567");
        bookInput.setBookName("Mr. Bean");
        bookInput.setAuthor("Mr. White");
        bookInput.setPublishTime(1922);

        Optional<BookDto> result = IBookService.add(bookInput);

        assertEquals(result.get().getIsbn(),bookInput.getIsbn());
    }

    @Test
    public void testBookQuery() {
        Book bookReturn = new Book();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);

        Mockito.when(bookMapper.queryById(Mockito.anyInt())).thenReturn(bookReturn);

        Optional<BookDto> result = IBookService.query(1);
        assertEquals("Mr. Bean", result.get().getBookName());
    }

    @Test
    public void testBookListAll() {
        Book bookReturn = new Book();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);

        List<Book> bookEoList = new ArrayList<>();
        bookEoList.add(bookReturn);

        Mockito.when(bookMapper.listAll()).thenReturn(bookEoList);

        Optional<List<BookDto>> result = IBookService.list();
        assertEquals(Boolean.TRUE.booleanValue(), result.get().size() > 0);
    }

    @Test
    public void testDelete() {
        Mockito.when(bookMapper.delete(Mockito.anyInt())).thenReturn(1);
        assertEquals(1, IBookService.delete(1));
    }

    @Test
    public void testUpdate(){
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setBookName("Mrs. J");
        bookDto.setAuthor("Mrs. B");

        Book bookReturn = new Book();
        bookReturn.setId(1);
        bookReturn.setIsbn("9787011334567");
        bookReturn.setBookName("Mr. Bean");
        bookReturn.setAuthor("Mr. White");
        bookReturn.setPublishTime(1922);


        Mockito.when(bookMapper.update(bookDto)).thenReturn(1);
        Mockito.when(bookMapper.queryById(1)).thenReturn(bookReturn);

        Optional<BookDto> update = IBookService.update(bookDto);

        BookDto updatedBookDto = update.get();
        assertEquals(updatedBookDto.getId(),1);
        assertEquals(updatedBookDto.getBookName(), "Mrs. J");
        assertEquals(updatedBookDto.getAuthor(),"Mrs. B");
        assertEquals(updatedBookDto.getPublishTime(), 1922);
    }

}
