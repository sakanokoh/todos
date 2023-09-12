package sn.ept.git.seminaire.cicd.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import sn.ept.git.seminaire.cicd.exceptions.handler.ExceptionHandlerTestHelper;
import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExceptionHandlerTestHelperTest {

    private final ExceptionHandlerTestHelper testHelper = new ExceptionHandlerTestHelper();

    @Mock
    private WebRequest mockWebRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    

    @Test
    public void testConflictExceptionHandler() {
        // Arrange
        ItemExistsException ex = new ItemExistsException("Item already exists");
        when(mockWebRequest.getDescription(false)).thenReturn("Request description");

        // Act
        ResponseEntity<ErrorMessage> responseEntity = testHelper.testConflictExceptionHandler(ex, mockWebRequest);

        // Assert
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        ErrorMessage errorMessage = responseEntity.getBody();
        assertEquals(HttpStatus.CONFLICT.value(), errorMessage.getStatus());
        assertEquals("Item already exists", errorMessage.getMessage());
        assertEquals("Request description", errorMessage.getDescription());
    }

    @Test
    public void testBadRequestExceptionHandler() {
        // Arrange
        InvalidException ex = new InvalidException("Invalid request");
        when(mockWebRequest.getDescription(false)).thenReturn("Request description");

        // Act
        ResponseEntity<ErrorMessage> responseEntity = testHelper.testBadRequestExceptionHandler(ex, mockWebRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorMessage errorMessage = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorMessage.getStatus());
        assertEquals("Invalid request", errorMessage.getMessage());
        assertEquals("Request description", errorMessage.getDescription());
    }

    @Test
    public void testPermissionDeniedExceptionHandler() {
        // Arrange
        ForbiddenException ex = new ForbiddenException("Access denied");
        when(mockWebRequest.getDescription(false)).thenReturn("Request description");

        // Act
        ResponseEntity<ErrorMessage> responseEntity = testHelper.testPermissionDeniedExceptionHandler(ex, mockWebRequest);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        ErrorMessage errorMessage = responseEntity.getBody();
        assertEquals(HttpStatus.FORBIDDEN.value(), errorMessage.getStatus());
        assertEquals("Access denied", errorMessage.getMessage());
        assertEquals("Request description", errorMessage.getDescription());
    }


    @Test
    public void testInternalErrorExceptionHandler() {
        // Arrange
        Exception ex = new Exception("Internal server error");
        when(mockWebRequest.getDescription(false)).thenReturn("Request description");

        // Act
        ResponseEntity<ErrorMessage> responseEntity = testHelper.testInternalErrorExceptionHandler(ex, mockWebRequest);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorMessage errorMessage = responseEntity.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage.getStatus());
        assertEquals("Internal server error", errorMessage.getMessage());
        assertEquals("Request description", errorMessage.getDescription());
    }

    @Test
    public void testNotFoundExceptionHandler() {
        // Arrange
        ItemNotFoundException ex = new ItemNotFoundException("Item not found");
        when(mockWebRequest.getDescription(false)).thenReturn("Request description");

        // Act
        ResponseEntity<ErrorMessage> responseEntity = testHelper.testNotFoundExceptionHandler(ex, mockWebRequest);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorMessage errorMessage = responseEntity.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), errorMessage.getStatus());
        assertEquals("Item not found", errorMessage.getMessage());
        assertEquals("Request description", errorMessage.getDescription()); // Vérifiez les détails de la requête
    }

}
