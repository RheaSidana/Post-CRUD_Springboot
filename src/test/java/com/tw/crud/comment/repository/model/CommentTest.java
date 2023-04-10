package com.tw.crud.comment.repository.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommentTest {
    @Test
    void shouldReturnViolationWhenAnyFieldIsNull() {
        Comment comment = new Comment(3L, "Author", null, "Comment");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldNotReturnViolationWhenAllFieldsAreProvided() {
        Comment comment = new Comment(3L, "Author", "Author@3", "Comment");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);

        assertTrue(violations.isEmpty());
    }
}