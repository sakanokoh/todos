package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemExistsExceptionTest {

    @Test
    void testDefaultConstructor() {
        ItemExistsException exception = new ItemExistsException("Test Message");
        assertEquals("Test Message", exception.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        ItemExistsException exception = new ItemExistsException("Test Message");
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testFormatMethod() {
        String formattedMessage = ItemExistsException.format("Le nom %s existe déjà", "John Doe");
        assertEquals("Le nom John Doe existe déjà", formattedMessage);
    }

    @Test
    void testItemExistsException() {
        ItemExistsException exception = new ItemExistsException();

        assertEquals(ItemExistsException.DEFAUL_MESSAGE, exception.getMessage());
    }


    @Test
    void testItemExistsExceptionWithMessageAndCause() {
        String customMessage = "Custom error message";
        Throwable customCause = new RuntimeException("Custom cause");

        ItemExistsException exception = new ItemExistsException(customMessage, customCause);

        assertEquals(customMessage, exception.getMessage());
        assertSame(customCause, exception.getCause());
    }

    @Test
    void testItemExistsExceptionWithCause() {
        Throwable customCause = new RuntimeException("Custom cause");

        ItemExistsException exception = new ItemExistsException(customCause);

        assertSame(customCause, exception.getCause());
    }
}

