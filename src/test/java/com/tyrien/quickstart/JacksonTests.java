package com.tyrien.quickstart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyrien.quickstart.domain.entities.Author;
import com.tyrien.quickstart.domain.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JacksonTests {
    @Test
    //This method is a test to convert a Java object into a Json object
    public void testThatObjectMapperCanCreateJSONFromJavaObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Author author = new Author(1L, "Abigail Rose", 80);
        Book book = Book.builder()
                .isbn("978-1-2345-6789-3")
                .title("The Engine of Eternity")
                .author(author)
                .build();

       String result = objectMapper.writeValueAsString(book);
       assertThat(result).isEqualTo("{\"isbn\":\"978-1-2345-6789-3\",\"title\":\"The Engine of Eternity\",\"author details\":{\"id\":1,\"name\":\"Abigail Rose\",\"age\":80}}");
    }

    @Test
    //This method is a test to convert a JSON object into a Java object
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JsonProcessingException {
        Author author = new Author(1L, "Abigail Rose", 80);
        Book book = Book.builder()
                .isbn("978-1-2345-6789-3")
                .title("The Engine of Eternity")
                .author(author)
                .build();

        String json = "{\"foo\":\"bar\",\"isbn\":\"978-1-2345-6789-3\",\"title\":\"The Engine of Eternity\",\"author details\":{\"id\":1,\"name\":\"Abigail Rose\",\"age\":80}}";

        final ObjectMapper objectMapper = new ObjectMapper();

        Book result = objectMapper.readValue(json, Book.class);
        assertThat(result).isEqualTo(book);
    }
}
