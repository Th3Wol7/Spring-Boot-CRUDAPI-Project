package com.tyrien.quickstart.services.impl;

import com.tyrien.quickstart.domain.entities.Author;
import com.tyrien.quickstart.repositories.AuthorRepository;
import com.tyrien.quickstart.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return StreamSupport.stream(authorRepository
                .findAll()
                .spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Author> find(Long author_id) {
        return authorRepository.findById(author_id);
    }

    @Override
    public boolean isExist(Long authorId) {
        return authorRepository.existsById( authorId);
    }

    @Override
    public Author partialUpdate(Long authorId, Author authorEntity) {
        authorEntity.setId(authorId);
        return authorRepository.findById(authorId).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author does not exist"));
    }

    @Override
    public void delete(Long authorId) {
        authorRepository.deleteById(authorId);
    }

}
