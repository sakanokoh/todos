package sn.ept.git.seminaire.cicd.utils;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResponseUtilTest {

    @Test
    void wrapOrNotFoundWithOptionalData() {
        Optional<String> maybeResponse = Optional.of("Hello, World!");
        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Hello, World!", responseEntity.getBody());
    }

    @Test
    void wrapOrNotFoundWithOptionalDataAndStatus() {
        Optional<Integer> maybeResponse = Optional.of(42);
        ResponseEntity<Integer> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, HttpStatus.ACCEPTED);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(42, responseEntity.getBody().intValue());
    }

    @Test
    void wrapOrNotFoundWithOptionalDataAndStatusAndReason() {
        Optional<Double> maybeResponse = Optional.of(3.14);
        ResponseEntity<Double> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, HttpStatus.NOT_FOUND, "Not found");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(3.14, responseEntity.getBody().doubleValue());
    }

    @Test
    void wrapOrNotFoundWithOptionalDataAndReason() {
        Optional<Boolean> maybeResponse = Optional.of(true);
        ResponseEntity<Boolean> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, "Custom reason");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
        
    }


    @Test
    void wrapOrNotFoundWithOptionalDataAndHeadersAndStatus() {
        Optional<String> maybeResponse = Optional.of("Hello");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Value");

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(
                maybeResponse, headers, HttpStatus.BAD_REQUEST);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Value", responseEntity.getHeaders().getFirst("Custom-Header"));
        assertEquals("Hello", responseEntity.getBody());
    }
    

    @Test
    void wrapOrNotFoundWithOptionalDataAndHeadersAndStatusAndReason1() {
        Optional<String> maybeResponse = Optional.of("Hello");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Value");

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(
                maybeResponse, headers, HttpStatus.BAD_REQUEST, "Bad request");

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Value", responseEntity.getHeaders().getFirst("Custom-Header"));
        assertEquals("Hello", responseEntity.getBody());
    }

    
}


