package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenExceptionTest {

    private ForbiddenException forbiddenException;

    @BeforeEach
    void setUp() {
        // Crée un objet ForbiddenException pour les tests
        forbiddenException = new ForbiddenException();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(ForbiddenException.DEFAULT_MSG, forbiddenException.getMessage());
    }

    @Test
    void testCustomMessageConstructor() {
        String customMessage = "Custom Forbidden Message";
        ForbiddenException customException = new ForbiddenException(customMessage);

        assertEquals(customMessage, customException.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        assertTrue(forbiddenException instanceof RuntimeException);
    }

    @Test
    void testResponseStatusAnnotation() {
        ResponseStatus responseStatusAnnotation = ForbiddenException.class.getAnnotation(ResponseStatus.class);

        assertNotNull(responseStatusAnnotation);
        assertEquals(HttpStatus.FORBIDDEN, responseStatusAnnotation.value());
        assertEquals(ForbiddenException.DEFAULT_MSG, responseStatusAnnotation.reason());
    }
}

