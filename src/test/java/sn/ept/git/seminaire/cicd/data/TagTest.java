package sn.ept.git.seminaire.cicd.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import sn.ept.git.seminaire.cicd.models.BaseEntity;
import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.models.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;





import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TagTest {

    @Autowired
    private TestEntityManager entityManager;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testNotBlankName() {
        Tag tag = new Tag();
        tag.setName(""); // Empty name, violates @NotBlank
        tag.setDescription("Description");

        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        assertEquals(2, violations.size()); 
    }

    @Test
    public void testSizeNameMin() {
        Tag tag = new Tag();
        tag.setName("A"); // Name length < min size, violates @Size
        tag.setDescription("Description");

        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        assertEquals(1, violations.size());
    }

    @Test
    public void testSizeNameMax() {
        Tag tag = new Tag();
        tag.setName("123456789012345678901234567890123aszdecfcececedcfcfcededdd"); // Name length > max size, violates @Size
        tag.setDescription("Description");

        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        assertEquals(1, violations.size());
    }

    @Test
    public void testSizeDescriptionMax() {
        Tag tag = new Tag();
        tag.setName("Name");
        tag.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor metus id nulla bibendum ultrices.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor metus id nulla bibendum ultrices.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor metus id nulla bibendum ultrices.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor metus id nulla bibendum ultrices."); // Description length > max size, violates @Size

        Set<ConstraintViolation<Tag>> violations = validator.validate(tag);
        assertEquals(1, violations.size());
    }

    @Test
    void testAllArgsConstructor() {
        // Création d'une instance de Tag à l'aide du constructeur généré par Lombok
        UUID id = UUID.randomUUID();
        String name = "Sample Name";
        String description = "Sample Description";
        Set<Todo> todos = new HashSet<>(); // Vous pouvez ajouter des Todos si nécessaire

        Tag tag = new Tag(name, description, todos);

        // Vérification des valeurs des champs
        assertNull(tag.getId()); // L'ID doit être null car il est généré automatiquement
        assertEquals(name, tag.getName());
        assertEquals(description, tag.getDescription());
        assertEquals(todos, tag.getTodos());

        // Vous pouvez également effectuer d'autres vérifications si nécessaire.
    }

    @Test
    void testDataAnnotation() {
        Tag tag = Tag.builder()
                .name("Test Tag")
                .description("Description du test")
                .build();

        // Vérifiez l'égalité avec un autre objet Tag créé de la même manière
        Tag anotherTag = Tag.builder()
                .name("Test Tag")
                .description("Description du test")
                .build();

        Tag thirdTag = Tag.builder()
                .name("Test Tag")
                .description("Description du test")
                .build();

        Tag fourthTag = Tag.builder()
                .name("Test2")
                .description("Description")
                .build();

        Object TagObject = Tag.builder()
            .name("Test2")
            .description("Description")
            .build();
        

        Object TagObject1 = Tag.builder()
            .name("Test2")
            .description("Description")
            .build();

        

        assertTrue(TagObject.equals(TagObject1) && TagObject1.equals(TagObject));
        assertEquals(TagObject.hashCode(), TagObject1.hashCode());
        assertEquals(TagObject.toString(), TagObject1.toString());
        


        assertTrue(tag.equals(tag));
        //symmetric: for any non-null values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
        assertTrue(tag.equals(anotherTag) && anotherTag.equals(tag));
        //transitive: for any non-null values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
        assertTrue(tag.equals(thirdTag) && anotherTag.equals(thirdTag) && tag.equals(anotherTag));
        //consistent: for any non-null values x and y, multiple invocations of x.equals(y) should consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
        assertTrue(tag.equals(anotherTag) && tag.equals(anotherTag) && tag.equals(anotherTag) && tag.equals(anotherTag));
        //Null check: for any non-null value x, x.equals(null) should return false.
        assertFalse(tag.equals(null));
        assertEquals(tag, anotherTag);
        assertEquals(tag.hashCode(), anotherTag.hashCode()); 
        assertEquals(tag.toString(), anotherTag.toString());
        assertEquals(tag.getName(), anotherTag.getName());
        assertEquals(tag.getDescription(), anotherTag.getDescription());



    }

    @Test
    public void testTagSpecificProperties() {
        // Crée un objet Tag
        Tag tag = Tag.builder()
                .name("Test Tag")
                .description("Description du test")
                .build();

        // Vérifiez que les propriétés spécifiques à Tag sont correctement définies
        assertEquals("Test Tag", tag.getName());
        assertEquals("Description du test", tag.getDescription());
    }

    @Test
    public void testInheritedProperties() {
        // Crée un objet Tag
        Tag tag = Tag.builder()
                .name("Test Tag")
                .description("Description du test")
                .build();

        // Génère un UUID pour le tag
        UUID tagId = UUID.randomUUID();

        // Affecte l'UUID au tag
        tag.setId(tagId);

        // Vérifiez que les propriétés héritées de BaseEntity sont correctement définies
        assertNotNull(tag.getId());
        assertTrue(tag.isEnabled());
        assertFalse(tag.isDeleted());

        // Assurez-vous que les horodatages sont corrects (peut nécessiter des ajustements en fonction de votre configuration)
        assertNotNull(tag.getCreatedDate());
        assertNotNull(tag.getLastModifiedDate());

        tag.setName("New Name");
        assertEquals("New Name", tag.getName());

        // Test du setter setDescription
        tag.setDescription("New Description");
        assertEquals("New Description", tag.getDescription());

        // Test du setter setVersion
        tag.setVersion(2);
        assertEquals(2, tag.getVersion());

        // Test du setter setEnabled
        tag.setEnabled(false);
        assertFalse(tag.isEnabled());

        // Test du setter setDeleted
        tag.setDeleted(true);
        assertTrue(tag.isDeleted());

        // Test du setter setCreatedDate (peut nécessiter des ajustements en fonction de votre configuration)
        Instant newCreatedDate = Instant.now();
        tag.setCreatedDate(newCreatedDate);
        assertEquals(newCreatedDate, tag.getCreatedDate());

        // Test du setter setLastModifiedDate (peut nécessiter des ajustements en fonction de votre configuration)
        Instant newLastModifiedDate = Instant.now();
        tag.setLastModifiedDate(newLastModifiedDate);
        assertEquals(newLastModifiedDate, tag.getLastModifiedDate());
    }

    @Test
    void testFromId() {
        // Créez un UUID de test
        UUID testId = UUID.randomUUID();

        // Appelez la méthode fromId pour créer une instance de Tag
        Tag tag = Tag.fromId(testId);

        // Vérifiez que l'instance de Tag a été créée avec l'UUID spécifié
        assertNotNull(tag);
        assertEquals(testId, tag.getId());
    }


}
