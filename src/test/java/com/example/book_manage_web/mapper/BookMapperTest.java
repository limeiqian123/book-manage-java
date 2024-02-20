package com.example.book_manage_web.mapper;

import com.example.book_manage_web.dto.BookDto;
import com.example.book_manage_web.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testBookAdd() {
        Book book = new Book();
        book.setIsbn("9787011234561");
        book.setBookName("A Little Prince");
        book.setAuthor("Joke");
        book.setPublishTime(1993);

        bookMapper.saveOne(book);

        assertNotNull(book.getId());
        assertEquals("A Little Prince", book.getBookName());

    }

    @Test
    public void testQueryById() {

        Book book = bookMapper.queryById(3);

        assertEquals("Mr. Bean", book.getBookName());

    }

    @Test
    public void testListAll() {

        List<Book> books = bookMapper.listAll();

        assertEquals("Mr. Bean", books.get(2).getBookName());
    }

    @Test
    public void testDelete() {

        Book book = new Book();
        book.setIsbn("9787011234662");
        book.setBookName("A Prince");
        book.setAuthor("Joker");
        book.setPublishTime(1993);

        bookMapper.saveOne(book);

        Integer id = book.getId();
        Integer count = bookMapper.delete(id);
        assertEquals(1, count);
    }

    @Test
    public void testUpdate(){

        Book book = new Book();
        book.setId(1);
        book.setIsbn("9787011234662");
        book.setBookName("Bleak House");
        book.setAuthor("Toms");
        book.setPublishTime(1993);

        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book,bookDto);

        Integer count = bookMapper.update(bookDto);

        assertTrue(count > 0);
    }
}
