package com.tyrien.quickstart.services;

import com.tyrien.quickstart.domain.entities.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createOrUpdateBook(String isbn, Book book); //Method for creating/updating a book

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Optional<Book> find(String isbn);

    boolean isExist(String isbn);

    Book partialUpdate(String isbn, Book bookEntity);

    void delete(String isbn);
}
