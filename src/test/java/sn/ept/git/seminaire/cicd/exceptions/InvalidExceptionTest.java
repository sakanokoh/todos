package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidExceptionTest {

    @Test
    void testDefaultConstructor() {
        InvalidException exception = new InvalidException("Test Message");
        assertEquals("Test Message", exception.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        InvalidException exception = new InvalidException("Test Message");
        assertTrue(exception instanceof RuntimeException);
    }
}

