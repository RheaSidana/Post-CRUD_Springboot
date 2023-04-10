package com.tw.crud.post.repository.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void shouldReturnViolation() {
        Post post = new Post("author@3",
                "Author",
                null,
                "public");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldReturnNoViolation() {
        Post post = new Post("author@3",
                "Author",
                "Content",
                "public");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        assertTrue(violations.isEmpty());
    }
}