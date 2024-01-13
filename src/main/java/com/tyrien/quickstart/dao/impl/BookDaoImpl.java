package com.tyrien.quickstart.dao.impl;
/*
import com.tyrien.quickstart.dao.BookDao;
import com.tyrien.quickstart.domain.entities.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO book(isbn, title, author_id) VALUES(?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthor_id() );
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM book WHERE isbn = ? LIMIT 1",
                new BookDaoImpl.BookRowMapper(), isbn);

        return results.stream().findFirst();
    }

    @Override
    public List<Book> findMany() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM book",
                new BookDaoImpl.BookRowMapper()
        );
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update(
                "UPDATE book SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(), book.getTitle(), book.getAuthor_id(), isbn
        );
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM book WHERE isbn = ?",
                isbn
        );
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .author_id(rs.getLong("author_id"))
                    .build();
        }
    }

}*/

