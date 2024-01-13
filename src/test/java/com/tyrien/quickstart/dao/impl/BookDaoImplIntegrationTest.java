package com.tyrien.quickstart.dao.impl;
/*
import com.tyrien.quickstart.TestDataUtil;
import com.tyrien.quickstart.dao.AuthorDao;
import com.tyrien.quickstart.dao.impl.BookDaoImpl;
import com.tyrien.quickstart.domain.entities.Author;
import com.tyrien.quickstart.domain.entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {
    private BookDaoImpl underTest;
    private AuthorDao authorDao;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBookA();
        book.setAuthor_id(author.getId());
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
       authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthor_id(author.getId());
        underTest.create(bookA);

        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthor_id(author.getId());
        underTest.create(bookB);

        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthor_id(author.getId());
        underTest.create(bookC);

        List<Book> result = underTest.findMany();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthor_id(authorA.getId());
        underTest.create(bookA);

        bookA.setTitle("UPDATED");
        underTest.update(bookA.getIsbn(), bookA);

        Optional<Book> result = underTest.findOne(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);
        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthor_id(authorA.getId());
        underTest.create(bookA);
        underTest.delete(bookA.getIsbn());
        Optional<Book> result = underTest.findOne(bookA.getIsbn());
        assertThat(result).isEmpty();
    }

}*/
