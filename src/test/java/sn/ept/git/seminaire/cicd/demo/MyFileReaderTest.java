package sn.ept.git.seminaire.cicd.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyFileReaderTest {

    private MyFileReader myFileReader;
    private String validFilePath;
    private String invalidFilePath;

    @BeforeEach
    void setUp() {
        myFileReader = new MyFileReader();
        validFilePath = "valid-file.txt";
        invalidFilePath = "invalid-file.txt";
    }

    @Test
    void readValidFile() throws IOException {
        String content = "Hello, World!\nThis is a test file.";
        Files.write(Path.of(validFilePath), content.getBytes());

        List<String> lines = myFileReader.read(validFilePath);

        assertEquals(2, lines.size());
        assertEquals("Hello, World!", lines.get(0));
        assertEquals("This is a test file.", lines.get(1));

        Files.delete(Path.of(validFilePath));
    }

    @Test
    void readInvalidFile() {
        // Attempt to read a non-existent file
        assertThrows(IOException.class, () -> myFileReader.read(invalidFilePath));
    }
}
