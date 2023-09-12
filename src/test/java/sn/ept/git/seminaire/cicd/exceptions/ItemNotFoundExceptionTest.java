package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;



public class ItemNotFoundExceptionTest {

    @Test
    void testDefaultConstructor() {
        ItemNotFoundException exception = new ItemNotFoundException("Test Message");
        assertEquals("Test Message", exception.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        ItemNotFoundException exception = new ItemNotFoundException("Test Message");
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testFormatMethod() {
        String formattedMessage = ItemNotFoundException.format("Impossible de retrouver un site avec l'identifiant %s", "123");
        assertEquals("Impossible de retrouver un site avec l'identifiant 123", formattedMessage);
    }

    @Test
    void testItemNotFoundExceptionDefaultConstructor() {
        ItemNotFoundException exception = new ItemNotFoundException();

        assertNotNull(exception);
        assertEquals("Impossible de retrouver l'élément recherché", exception.getMessage());
    }

    @Test
    void testItemNotFoundExceptionWithMessageAndCause() {
        String customMessage = "Custom error message";
        Throwable customCause = mock(Throwable.class);

        ItemNotFoundException exception = new ItemNotFoundException(customMessage, customCause);

        assertNotNull(exception);
        assertEquals(customMessage, exception.getMessage());
        assertEquals(customCause, exception.getCause());
    }

    @Test
    void testItemNotFoundExceptionWithCause() {
        Throwable customCause = new Throwable("Custom cause message");

        ItemNotFoundException exception = new ItemNotFoundException(customCause);

        assertNotNull(exception);
        assertEquals(customCause, exception.getCause());
    }
}
