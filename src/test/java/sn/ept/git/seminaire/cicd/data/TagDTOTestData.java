package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.dto.base.TagBaseDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public final class TagDTOTestData extends TestData {

    public static TagDTO defaultDTO() {
        return TagDTO
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .name(Default.name)
                .description(Default.description)
                .build();
    }

    public static TagDTO updatedDTO() {
        return TagDTO
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .name(Update.name)
                .description(Update.description)
                .build();
    }


    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testNameNotBlank() {
        TagBaseDTO tagBaseDTO = TagBaseDTO.builder()
                .description("Test Description")
                .build();

        Set<ConstraintViolation<TagBaseDTO>> violations = validator.validate(tagBaseDTO);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    void testNameSizeValid() {
        String validName = "ValidName";
        TagBaseDTO tagBaseDTO = TagBaseDTO.builder()
                .name(validName)
                .description("Test Description")
                .build();

        Set<ConstraintViolation<TagBaseDTO>> violations = validator.validate(tagBaseDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNameSizeTooShort() {
        String shortName = "A"; // Below min size
        TagBaseDTO tagBaseDTO = TagBaseDTO.builder()
                .name(shortName)
                .description("Test Description")
                .build();

        Set<ConstraintViolation<TagBaseDTO>> violations = validator.validate(tagBaseDTO);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    void testNameSizeTooLong() {
        String longName = "ThisIsAReallyLongNameThatExceedsTheMaximumAllowedSize"; // Above max size
        TagBaseDTO tagBaseDTO = TagBaseDTO.builder()
                .name(longName)
                .description("Test Description")
                .build();

        Set<ConstraintViolation<TagBaseDTO>> violations = validator.validate(tagBaseDTO);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }


    @Test
    void testDataAnnotation() {
        // Créez une instance de TagBaseDTO pour les tests
        TagBaseDTO tagDTO = TagBaseDTO.builder()
                .name("Tag Name")
                .description("Tag Description")
                .build();

        // Créez une autre instance de TagBaseDTO avec les mêmes valeurs pour les tests d'égalité
        TagBaseDTO anotherTagDTO = TagBaseDTO.builder()
                .name("Tag Name")
                .description("Tag Description")
                .build();

        // Créez une troisième instance de TagBaseDTO avec des valeurs différentes
        TagBaseDTO thirdTagDTO = TagBaseDTO.builder()
                .name("Another Tag")
                .description("Another Description")
                .build();

        // Testez les conditions d'égalité
        assertTrue(tagDTO.equals(tagDTO));
        assertTrue(tagDTO.equals(anotherTagDTO) && anotherTagDTO.equals(tagDTO));
        assertTrue(tagDTO.hashCode() == anotherTagDTO.hashCode());
        assertTrue(tagDTO.toString().equals(anotherTagDTO.toString()));
        assertTrue(tagDTO.getName().equals(anotherTagDTO.getName()));
        assertTrue(tagDTO.getDescription().equals(anotherTagDTO.getDescription()));
        assertFalse(tagDTO.equals(null));

        // Testez avec le troisième objet pour vérifier la non-égalité
        assertFalse(tagDTO.equals(thirdTagDTO));
        assertFalse(tagDTO.hashCode() == thirdTagDTO.hashCode());
        assertFalse(tagDTO.toString().equals(thirdTagDTO.toString()));
    }



    //TagBase


    @Test
    void testAllArgsConstructor() {
        // Création d'une instance de TagBaseDTO à l'aide du constructeur généré par Lombok
        String name = "Sample Name";
        String description = "Sample Description";
        TagBaseDTO tagBaseDTO = new TagBaseDTO(name, description);

        // Vérification des valeurs des champs
        assertEquals(name, tagBaseDTO.getName());
        assertEquals(description, tagBaseDTO.getDescription());

        // Vous pouvez également effectuer d'autres vérifications si nécessaire.
    }


    @Test
    void testNoArgsConstructor() {
        // Création d'une instance de TagDTO en utilisant le constructeur par défaut généré par Lombok
        TagDTO tagDTO = new TagDTO();

        // Vérification que l'instance a été créée sans exception
        assertNotNull(tagDTO);

        // Vous pouvez également effectuer d'autres vérifications si nécessaire.
    }

    @Test
    void testGettersAndSetters() {
        TagBaseDTO tagDTO = new TagBaseDTO();

        // Utilisez les setters pour définir les valeurs
        tagDTO.setName("Tag Name");
        tagDTO.setDescription("Tag Description");

        // Utilisez les getters pour obtenir les valeurs
        assertEquals("Tag Name", tagDTO.getName());
        assertEquals("Tag Description", tagDTO.getDescription());

        // Modifiez les valeurs en utilisant les setters
        tagDTO.setName("New Tag Name");
        tagDTO.setDescription("New Tag Description");

        // Vérifiez les nouvelles valeurs en utilisant les getters
        assertEquals("New Tag Name", tagDTO.getName());
        assertEquals("New Tag Description", tagDTO.getDescription());
    }

    @Test
    void testToString() {
        TagBaseDTO tagDTO = TagBaseDTO.builder()
                .name("Tag Name")
                .description("Tag Description")
                .build();

        // Vérifiez que la méthode toString génère la représentation attendue
        String expectedToString = "TagBaseDTO(name=Tag Name, description=Tag Description)";
        assertEquals(expectedToString, tagDTO.toString());
    }
}
