package com.tyrien.quickstart;

import com.tyrien.quickstart.domain.dataTransferObjects.AuthorDto;
import com.tyrien.quickstart.domain.dataTransferObjects.BookDto;
import com.tyrien.quickstart.domain.entities.Author;
import com.tyrien.quickstart.domain.entities.Book;

public final class TestDataUtil {
    private TestDataUtil(){
    }

    public static Author createTestAuthorA() {
        Author author = Author.builder()
                .id(1L)
                 .name("Abigail Rose")
                .age(80)
                .build();
        return author;
    }

    public static AuthorDto createTestAuthorDtoA() {
        AuthorDto authorDto = AuthorDto.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
        return authorDto;
    }

    public static Author createTestAuthorB() {
        Author author = Author.builder()
                .id(2L)
                .name("Thomas Cronin")
                .age(44)
                .build();
        return author;
    }
    public static Author createTestAuthorC() {
        Author author = Author.builder()
                .id(3L)
                .name("Jesse A Cassey")
                .age(24)
                .build();
        return author;
    }

    public static Book createTestBookA(Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }
    public static Book createTestBookB(Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond The Horizon")
                .author(author)
                .build();
    }

    public static Book createTestBookC(Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .author(author)
                .build();
    }

    public static BookDto createTestBookDtoA(AuthorDto author) {
        return BookDto.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    /*public static Book createTestBookB() {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("Beyond The Horizon")
                .author_id(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Last Ember")
                .author_id(1L)
                .build();
    }*/
}
