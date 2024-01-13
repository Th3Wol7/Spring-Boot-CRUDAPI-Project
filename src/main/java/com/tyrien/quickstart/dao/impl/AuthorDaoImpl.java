package com.tyrien.quickstart.dao.impl;
/*
import com.tyrien.quickstart.dao.AuthorDao;
import com.tyrien.quickstart.domain.entities.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO author(id, name, age) VALUES(?, ?, ?)",
                author.getId(), author.getName(), author.getAge());
    }

    @Override
    public Optional<Author> findOne(long author_id){
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM author WHERE id = ? LIMIT 1",
                new AuthorRowMapper(), author_id);

        return results.stream().findFirst();
    }

    @Override
    public List<Author> find() {
       return jdbcTemplate.query(
                "SELECT id, name, age FROM author",
                new AuthorRowMapper()
        );
    }

    @Override
    public void update(long id, Author author) {
        jdbcTemplate.update(
                "UPDATE author SET id = ?, name = ?, age = ? WHERE id = ?",
                author.getId(), author.getName(), author.getAge(), id
        );
    }

    @Override
    public void delete(long author_id) {
        jdbcTemplate.update(
                "DELETE FROM author WHERE id = ?",
                author_id);
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}*/
