package com.example.book_manage_web.controller;

import com.example.book_manage_web.dto.BookDto;
import com.example.book_manage_web.exception.ExceptionEnum;
import com.example.book_manage_web.service.IBookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IBookService bookService;

    @Test
    public void testBookAdd() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn("9787011234682");
        bookDto.setBookName("Mr. Bean");
        bookDto.setAuthor("Mr. L");
        bookDto.setPublishTime(1988);
        Optional<BookDto> mockBookDto = Optional.of(bookDto);

        Mockito.when(bookService.add(Mockito.any(BookDto.class))).thenReturn(mockBookDto);

        String params = "{\"isbn\":\"9784676855123\",\"bookName\":\"Mr. Book\",\"author\":\"Jake\",\"publishTime\":\"2012\"}";
        MvcResult mvcResult = mockMvc.perform(post("/book/add")
                        .content(params)
                        .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.bookName").value("Mr. Bean"))
                .andReturn();
        ;
    }

    @Test
    public void testBookQuery() throws Exception {

        BookDto bookDto = new BookDto();
        bookDto.setIsbn("9787011234682");
        bookDto.setBookName("Mr. Bean");
        bookDto.setAuthor("Mr. L");
        bookDto.setPublishTime(1988);
        Optional<BookDto> mockBookDto = Optional.of(bookDto);

        Mockito.when(bookService.query(1)).thenReturn(mockBookDto);


        mockMvc.perform(get("/book/get/{id}", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.bookName").value("Mr. Bean"));
    }

    @Test
    public void testBookList() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn("9787011234682");
        bookDto.setBookName("Mr. Bean");
        bookDto.setAuthor("Mr. L");
        bookDto.setPublishTime(1888);

        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(bookDto);

        Optional<List<BookDto>> bookDtoList = Optional.of(bookDtos);

        Mockito.when(bookService.list()).thenReturn(bookDtoList);

        mockMvc.perform(get("/book/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].bookName").value("Mr. Bean"));
    }

    @Test
    public void testBookDelete() throws Exception {
        Mockito.when(bookService.delete(Mockito.anyInt())).thenReturn(1);
        mockMvc.perform(delete("/book/delete/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"));

    }

    @Test
    public void testBookUpdate() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setBookName("Mrs. Bean");
        bookDto.setAuthor("Mrs. B");

        BookDto bookReturn = new BookDto();
        BeanUtils.copyProperties(bookDto, bookReturn);

        Optional<BookDto> res = Optional.of(bookReturn);

        Mockito.when(bookService.update(bookDto)).thenReturn(res);

        String params = "{\"id\":1,\"bookName\":\"Mrs. Bean\",\"author\":\"Mrs. B\"}";
        mockMvc.perform(put("/book/update")
                        .content(params)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("Mrs. B"));
    }

    @Test
    public void testGlobalExceptionHandler() throws Exception {
        doThrow(new RuntimeException("unknown exception")).when(bookService).list();

        mockMvc.perform(get("/book/list"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ExceptionEnum.INTERNAL_SERVER_ERROR.getResultCode()));
    }

}
