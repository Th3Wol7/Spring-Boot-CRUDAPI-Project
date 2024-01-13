package com.tyrien.quickstart.services;

import com.tyrien.quickstart.domain.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author save(Author author);

    List<Author> findAll();

    Optional<Author> find(Long author_id);

    boolean isExist(Long authorId);

    Author partialUpdate(Long authorId, Author authorEntity);

    void delete(Long authorId);
}
