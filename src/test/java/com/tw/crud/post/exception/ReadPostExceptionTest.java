package com.tw.crud.post.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReadPostExceptionTest {

    public void exceptionTesting() throws ReadPostException {
        throw new ReadPostException("Post not found");
    }

    @Test
    void shouldThrowExceptionWhenPostNotFound() {
        ReadPostException readPostException = assertThrows(ReadPostException.class,()-> exceptionTesting());
        String exceptionMessage = "Post not found";

        assertEquals(exceptionMessage, readPostException.getMessage());
    }
}