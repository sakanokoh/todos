package sn.ept.git.seminaire.cicd.demo;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.demo.exception.BadPhoneException;

import static org.junit.jupiter.api.Assertions.*;

public class BadPhoneExceptionTest {

    @Test
    public void testConstructor() {
        BadPhoneException exception = new BadPhoneException();
        assertNotNull(exception);
    }

    @Test
    public void testMessageConstructor() {
        String message = "This is a bad phone exception.";
        BadPhoneException exception = new BadPhoneException(message);
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testMessageAndCauseConstructor() {
        String message = "This is a bad phone exception message.";
        Throwable cause = new Throwable("This is the cause.");
        BadPhoneException exception = new BadPhoneException(message, cause);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testCauseConstructor() {
        Throwable cause = new Throwable("This is the cause.");
        BadPhoneException exception = new BadPhoneException(cause);

        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testFullConstructor() {
        String message = "This is a bad phone exception message.";
        Throwable cause = new Throwable("This is the cause.");
        boolean enableSuppression = false;
        boolean writableStackTrace = true;

        BadPhoneException exception = new BadPhoneException(message, cause, enableSuppression, writableStackTrace);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(enableSuppression, exception.getSuppressed().length != 0);
        assertEquals(writableStackTrace, exception.getStackTrace().length > 0);
    }
}

