package com.tyrien.quickstart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyrien.quickstart.TestDataUtil;
import com.tyrien.quickstart.domain.dataTransferObjects.BookDto;
import com.tyrien.quickstart.domain.entities.Book;
import com.tyrien.quickstart.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsHttpStatus201Created() throws Exception {
        BookDto bookDto= TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsSavedBook() throws Exception {
        BookDto bookDto= TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor())
        );
    }

    @Test
    public void testThatListBooksReturnHttpsStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListBooksReturnBook() throws Exception {
        Book testbookEntityA = TestDataUtil.createTestBookA(null);
        bookService.createOrUpdateBook(testbookEntityA.getIsbn(), testbookEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].title").value("The Shadow in the Attic")
        );
    }

    @Test
    public void testThatRetrieveBookReturnHttpsStatus200WhenBookExist() throws Exception {
        Book testbookEntityA = TestDataUtil.createTestBookA(null);
        bookService.createOrUpdateBook(testbookEntityA.getIsbn(), testbookEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/978-1-2345-6789-0")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatRetrieveBookReturnHttpsStatus200WhenNoBookExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/978-1-2345-9999-9999")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveBookReturnWhenBookExist() throws Exception {
        Book testbookEntityA = TestDataUtil.createTestBookA(null);
        bookService.createOrUpdateBook(testbookEntityA.getIsbn(), testbookEntityA);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/978-1-2345-6789-0")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("The Shadow in the Attic")
        );
    }

    @Test
    public void testThatFullUpdateBookReturnHttpStatus200Ok() throws Exception {
        Book testBookEntityA = TestDataUtil.createTestBookA(null);
        Book savedBookEntity =  bookService.createOrUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);
        BookDto testBookDtoA = TestDataUtil.createTestBookDtoA(null);
        testBookDtoA.setIsbn(savedBookEntity.getIsbn());
        String bookDtoJson =  objectMapper.writeValueAsString(testBookDtoA);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+savedBookEntity.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testThatFullUpdateBookReturnsUpdatedBook() throws Exception {
        Book testBookEntityA = TestDataUtil.createTestBookA(null);
        Book savedBookEntity =  bookService.createOrUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);
        BookDto testBookDtoA = TestDataUtil.createTestBookDtoA(null);
        testBookDtoA.setIsbn(savedBookEntity.getIsbn());
        testBookDtoA.setTitle("UPDATED");
        String bookDtoJson =  objectMapper.writeValueAsString(testBookDtoA);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+ savedBookEntity.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus200OK()throws Exception {
        Book testbookEntityA = TestDataUtil.createTestBookA(null);
        bookService.createOrUpdateBook(testbookEntityA.getIsbn(), testbookEntityA);

        BookDto testBookDtoA = TestDataUtil.createTestBookDtoA(null);
        testBookDtoA.setTitle("UPDATED");
        String bookDtoJson =  objectMapper.writeValueAsString(testBookDtoA);

        mockMvc.perform(MockMvcRequestBuilders.patch("/books/"+testbookEntityA.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook()throws Exception {
        Book testbookEntityA = TestDataUtil.createTestBookA(null);
        bookService.createOrUpdateBook(testbookEntityA.getIsbn(), testbookEntityA);

        BookDto testBookDtoA = TestDataUtil.createTestBookDtoA(null);
        testBookDtoA.setTitle("UPDATED");
        String bookDtoJson =  objectMapper.writeValueAsString(testBookDtoA);

        mockMvc.perform(MockMvcRequestBuilders.patch("/books/"+testbookEntityA.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(testbookEntityA.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("UPDATED")
        );
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForNonExistingBooks() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/999")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForExistingBooks() throws Exception{
        Book testBookEntityA = TestDataUtil.createTestBookA(null);
        Book savedBookEntity =  bookService.createOrUpdateBook(testBookEntityA.getIsbn(), testBookEntityA);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/"+savedBookEntity.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());

    }


}
