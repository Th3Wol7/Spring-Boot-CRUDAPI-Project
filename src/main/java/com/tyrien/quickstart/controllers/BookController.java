package com.tyrien.quickstart.controllers;

import com.tyrien.quickstart.domain.dataTransferObjects.BookDto;
import com.tyrien.quickstart.domain.entities.Book;
import com.tyrien.quickstart.mappers.Mapper;
import com.tyrien.quickstart.services.BookService;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Log
public class BookController {
    private BookService bookService;
    private Mapper<Book, BookDto> bookMapper;

    public BookController( BookService bookService, Mapper<Book, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping(path = "/books")
    public Page<BookDto> retrieveBooks(Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

    /*@GetMapping(path = "/books")
    public List<BookDto> retrieveBooks(){
        List<Book> books = bookService.findAll();
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }*/

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> retrieveBook(@PathVariable("isbn") String isbn){
        Optional<Book> bookFound = bookService.find(isbn);
        return bookFound.map(book ->{
            BookDto bookDto = bookMapper.mapTo(book);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        } ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createOrUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto){
        Book bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExist = bookService.isExist(isbn);
        Book savedBookEntity = bookService.createOrUpdateBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);

        if(bookExist){//if book exist update it
            bookDto.setIsbn(isbn);
            return new ResponseEntity<>(savedBookDto,                    HttpStatus.OK);
        }else{//if book does exist create it
            log.info("Got Book: " + bookDto.toString());
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto) {
        if (!bookService.isExist(isbn)) {//if book exist update it
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book bookEntity = bookMapper.mapFrom(bookDto);
        Book updatedBookEntity = bookService.partialUpdate(isbn, bookEntity);
        return new ResponseEntity<>(
                bookMapper.mapTo(updatedBookEntity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn){
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
