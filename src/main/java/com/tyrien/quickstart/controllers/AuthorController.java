package com.tyrien.quickstart.controllers;

import com.tyrien.quickstart.domain.dataTransferObjects.AuthorDto;
import com.tyrien.quickstart.domain.entities.Author;
import com.tyrien.quickstart.mappers.Mapper;
import com.tyrien.quickstart.services.AuthorService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Log
public class AuthorController {

    private AuthorService authorService;
    private Mapper<Author, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> retrieveAuthors(){
        List<Author> authors = authorService.findAll();
        return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> retrieveAuthor(@PathVariable("id") Long author_id){
        Optional<Author> authorFound = authorService.find(author_id);
        return authorFound.map(author ->{
           AuthorDto authorDto = authorMapper.mapTo(author);
           return new ResponseEntity<>(authorDto, HttpStatus.OK);
        } ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody final AuthorDto author){
        log.info("Got Author: " + author.toString());
        Author authorEntity = authorMapper.mapFrom(author);
        Author savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(
            @PathVariable("id") Long author_id,
            @RequestBody AuthorDto authorDto){
        if(!authorService.isExist(author_id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDto.setId(author_id);
        Author authorEntity = authorMapper.mapFrom(authorDto);
        Author savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdate(
            @PathVariable("id") Long author_id,
            @RequestBody AuthorDto authorDto){
        if(!authorService.isExist(author_id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author authorEntity = authorMapper.mapFrom(authorDto);
        Author updatedAuthor = authorService.partialUpdate(author_id, authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthor), HttpStatus.OK);
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long author_id){
        authorService.delete(author_id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
