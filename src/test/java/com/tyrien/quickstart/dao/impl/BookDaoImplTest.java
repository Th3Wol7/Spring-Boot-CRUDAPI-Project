package com.tyrien.quickstart.dao.impl;
/*
import com.tyrien.quickstart.TestDataUtil;
import com.tyrien.quickstart.domain.entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest{
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreatBookGeneratesSql(){
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO book(isbn, title, author_id) VALUES(?, ?, ?)"),
                eq("978-1-2345-6789-0"), eq("The Shadow in the Attic"), eq(1L));

    }

    @Test
    public void testThatFindOneBookGenerateTheCorrectSql(){
        underTest.findOne("978-1-2345-6789-0");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM book WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("978-1-2345-6789-0")
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql(){
        underTest.findMany();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM book"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        underTest.update("978-1-2345-6789-0", book);

        verify(jdbcTemplate).update(
                "UPDATE book SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "978-1-2345-6789-0", "The Shadow in the Attic", 1L, "978-1-2345-6789-0"
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        underTest.delete("978-1-2345-6789-0");

        verify(jdbcTemplate).update(
                "DELETE FROM book WHERE isbn = ?",
                "978-1-2345-6789-0"
        );
    }
}*/


