package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import org.apache.commons.lang.RandomStringUtils;
import java.time.Instant;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.dto.base.BaseDTO;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.models.Todo;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TestData {

    public TestData(){}

    public static final class Default {
        private Default(){}
        public static final UUID id = UUID.randomUUID();
        public static final Instant createdDate = Instant.now();
        public static final Instant lastModifiedDate = Instant.now();
        public static final int version = 0;
        public static final boolean enabled = true;
        public static final boolean deleted = false;
        public static final  String title = RandomStringUtils.randomAlphanumeric( (SizeMapping.Title.MIN+SizeMapping.Title.MAX)/2);
        public static final  String name = RandomStringUtils.randomAlphanumeric( (SizeMapping.Name.MIN+SizeMapping.Name.MAX)/2);
        public static final  String description=  RandomStringUtils.randomAlphanumeric( (SizeMapping.Description.MIN+SizeMapping.Description.MAX)/2);
    }


    public static final class Update {
        private Update(){}
        public static final UUID id = UUID.randomUUID();
        public static final Instant createdDate = Instant.now();
        public static final Instant lastModifiedDate = Instant.now();
        public static final int version = 2;
        public static final boolean enabled = false;
        public static final boolean deleted = true;
        public static final  String title = RandomStringUtils.randomAlphanumeric( (SizeMapping.Title.MIN+SizeMapping.Title.MAX)/2);
        public static final  String name = RandomStringUtils.randomAlphanumeric( (SizeMapping.Name.MIN+SizeMapping.Name.MAX)/2);
        public static final  String description=  RandomStringUtils.randomAlphanumeric( (SizeMapping.Description.MIN+SizeMapping.Description.MAX)/2);
    }


    

    @Test //Base DTO
    void testJsonProperty() {
        Field[] fields = BaseDTO.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonProperty.class)) {
                JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                assertNotNull(jsonProperty);
                assertNotNull(jsonProperty.value());
            }
        }
    }

    @Test // Base DTO
    void testAllArgsConstructor() {
        assertDoesNotThrow(() -> {
            BaseDTO.class.getDeclaredConstructor(UUID.class, Instant.class, Instant.class, int.class, boolean.class, boolean.class);
        });
    }

    @Test  // Base DTO
    void testNoArgsConstructor() {
        assertDoesNotThrow(() -> {
            BaseDTO.class.getDeclaredConstructor();
        });
    }

    @Test  // Base DTO 
    void testSuperBuilder() {
        BaseDTO baseDTO = BaseDTO.builder().build();
        assertNotNull(baseDTO);
    }

    @Test // Base DTO
    void testEqualsAndHashCode() {
        BaseDTO baseDTO1 = BaseDTO.of(TestData.Default.id, TestData.Default.createdDate, TestData.Default.lastModifiedDate,
                TestData.Default.version, TestData.Default.enabled, TestData.Default.deleted);
        BaseDTO baseDTO2 = BaseDTO.of(TestData.Default.id, TestData.Default.createdDate, TestData.Default.lastModifiedDate,
                TestData.Default.version, TestData.Default.enabled, TestData.Default.deleted);

        assertTrue(baseDTO1.equals(baseDTO2) && baseDTO2.equals(baseDTO1));
        assertEquals(baseDTO1.hashCode(), baseDTO2.hashCode());
    }

    @Test // Base DTO
    void testGetterSetter() {
        BaseDTO baseDTO = BaseDTO.of(TestData.Default.id, TestData.Default.createdDate, TestData.Default.lastModifiedDate,
                TestData.Default.version, TestData.Default.enabled, TestData.Default.deleted);

        assertNotNull(baseDTO.getId());
        assertNotNull(baseDTO.getCreatedDate());
        assertNotNull(baseDTO.getLastModifiedDate());
        assertTrue(baseDTO.getVersion() >= 0);
        assertTrue(baseDTO.isEnabled());
        assertFalse(baseDTO.isDeleted());

        baseDTO.setId(null);
        assertNull(baseDTO.getId());
    }



        @Test  // Base DTO
        void testToStringAnnotation() {
        BaseDTO dto = BaseDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .version(1)
                .enabled(true)
                .deleted(false)
                .build();

        // Utilisation de la méthode toString() générée par Lombok
        String toStringResult = dto.toString();
        assertNotNull(toStringResult);
}
}
