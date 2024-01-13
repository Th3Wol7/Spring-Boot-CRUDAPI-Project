package com.tyrien.quickstart.repositories;

import com.tyrien.quickstart.domain.entities.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> ageLessThan(int age);//No need to implement because Spring JPA can determine the

    @Query("SELECT a FROM Author a WHERE a.age > ?1") //Uses HQL to help spring to determine the functionality ot the method
    List<Author> findAuthorsWithAgeGreaterThan(int age);
    //functionality of the method solely based on how it's named.
}
