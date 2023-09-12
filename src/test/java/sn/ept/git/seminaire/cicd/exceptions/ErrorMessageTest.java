package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import nl.jqno.equalsverifier.EqualsVerifier;

public class ErrorMessageTest {

    private ErrorMessage errorMessage;

    @BeforeEach
    void setUp() {
        // Crée un objet ErrorMessage pour les tests
        errorMessage = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
    }

    @Test
    void testGetStatus() {
        assertEquals(404, errorMessage.getStatus());
    }

    @Test
    void testGetTimestamp() {
        // Vous pouvez ajuster cette vérification en fonction de votre besoin
        assertEquals(new Date().toString(), errorMessage.getTimestamp().toString());
    }

    @Test
    void testGetMessage() {
        assertEquals("Not Found", errorMessage.getMessage());
    }

    @Test
    void testGetDescription() {
        assertEquals("Resource not found", errorMessage.getDescription());
    }

    

    @Test
    void testToString() {
        String expectedToString = "ErrorMessage(status=404, timestamp=" + errorMessage.getTimestamp() +
                ", message=Not Found, description=Resource not found)";
        assertEquals(expectedToString, errorMessage.toString());
    }

    @Test
    void testSetter() {
        ErrorMessage errorMessage = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
        errorMessage.setStatus(500);
        errorMessage.setTimestamp(new Date());
        errorMessage.setMessage("Internal Server Error");
        errorMessage.setDescription("Something went wrong");

        assertEquals(500, errorMessage.getStatus());
        assertNotNull(errorMessage.getTimestamp());
        assertEquals("Internal Server Error", errorMessage.getMessage());
        assertEquals("Something went wrong", errorMessage.getDescription());
    }

    @Test
    void testNoArgsConstructor() {
        ErrorMessage errorMessage = new ErrorMessage();
        assertNull(errorMessage.getMessage());
        assertNull(errorMessage.getTimestamp());
        assertEquals(0, errorMessage.getStatus());
        assertNull(errorMessage.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        ErrorMessage errorMessage1 = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
        ErrorMessage errorMessage2 = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
        ErrorMessage errorMessage3 = ErrorMessage.of(500, new Date(), "Internal Server Error", "Something went wrong");
        ErrorMessage errorMessage4 = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");

        assertEquals(errorMessage1, errorMessage2);
        assertNotEquals(errorMessage1, errorMessage3);
        assertNotEquals(errorMessage2, errorMessage3);
        assertEquals(errorMessage1, errorMessage1);
        assertEquals(errorMessage2, errorMessage1);
        assertEquals(errorMessage1, errorMessage4);
        assertEquals(errorMessage2, errorMessage4);

        // Tester l'inégalité avec une autre classe
        assertNotEquals(errorMessage1, new Object());

        // Tester l'égalité des hashCode pour des instances égales
        assertEquals(errorMessage1.hashCode(), errorMessage2.hashCode());

        assertEquals(errorMessage1.hashCode(), errorMessage2.hashCode());
        assertNotEquals(errorMessage1.hashCode(), errorMessage3.hashCode());
        assertNotEquals(errorMessage2.hashCode(), errorMessage3.hashCode());
    }



    @Test
    void testSuperBuilder() {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(400)
                .timestamp(new Date())
                .message("Bad Request")
                .description("Invalid input")
                .build();

        assertEquals(400, errorMessage.getStatus());
        assertNotNull(errorMessage.getTimestamp());
        assertEquals("Bad Request", errorMessage.getMessage());
        assertEquals("Invalid input", errorMessage.getDescription());
    }
}

