package sn.ept.git.seminaire.cicd.utils;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestUtilTest {

    @Test
    void testConvertObjectToJsonBytes() throws IOException {
        Map<String, String> sampleMap = new HashMap<>();
        sampleMap.put("key1", "value1");
        sampleMap.put("key2", "value2");

        byte[] jsonBytes = TestUtil.convertObjectToJsonBytes(sampleMap);

        assertNotNull(jsonBytes);
        assertArrayEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}".getBytes(), jsonBytes);
    }

    @Test
    void testCreateByteArray() {
        int size = 4;
        String data = "1010";

        byte[] byteArray = TestUtil.createByteArray(size, data);

        assertNotNull(byteArray);
        assertArrayEquals(new byte[]{(byte) 10, (byte) 10, (byte) 10, (byte) 10}, byteArray);
    }

    
}

