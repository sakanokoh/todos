package sn.ept.git.seminaire.cicd.demo;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.demo.exception.DivisionByZeroException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DivisionByZeroExceptionTest {

    @Test
    public void testDefaultConstructor() {
        DivisionByZeroException exception = new DivisionByZeroException();

        assertNotNull(exception);
    }

    @Test
    public void testMessageAndCauseConstructor() {
        String message = "This is a division by zero exception message.";
        Throwable cause = new Throwable("This is the cause.");
        DivisionByZeroException exception = new DivisionByZeroException(message, cause);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testCauseConstructor() {
        Throwable cause = new Throwable("This is the cause.");
        DivisionByZeroException exception = new DivisionByZeroException(cause);

        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testFullConstructor() {
        String message = "This is a division by zero exception message.";
        Throwable cause = new Throwable("This is the cause.");
        boolean enableSuppression = false;
        boolean writableStackTrace = true;

        DivisionByZeroException exception = new DivisionByZeroException(message, cause, enableSuppression, writableStackTrace);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(enableSuppression, exception.getSuppressed().length != 0);
        assertEquals(writableStackTrace, exception.getStackTrace().length > 0);
    }
}

