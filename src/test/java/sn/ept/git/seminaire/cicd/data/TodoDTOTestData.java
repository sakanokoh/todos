package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.TodoDTO;
import sn.ept.git.seminaire.cicd.dto.base.TodoBaseDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public final class TodoDTOTestData extends TestData {

    public static TodoDTO defaultDTO() {
        return TodoDTO
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .title(Default.title)
                .description(Default.description)
                .tags(Stream.of(TagDTOTestData.defaultDTO()).collect(Collectors.toSet()))
                .build();
    }

    public static TodoDTO updatedDTO() {
        return TodoDTO
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .title(Update.title)
                .description(Update.description)
                .tags(Stream.of(TagDTOTestData.updatedDTO()).collect(Collectors.toSet()))
                .build();
    }


   

    @Test
    void testNoArgsConstructor() {
        // Création d'une instance de TodoDTO à l'aide du constructeur par défaut généré par Lombok
        TodoDTO todoDTO = new TodoDTO();

        // Vérification que l'instance a été créée avec succès sans provoquer d'exception
        assertNotNull(todoDTO);

        // Vous pouvez également effectuer d'autres vérifications si nécessaire.
    }

    @Test
    void testAllArgsConstructor() {
        // Création d'une instance de TodoBaseDTO à l'aide du constructeur généré par Lombok
        String title = "Sample Title";
        String description = "Sample Description";
        boolean completed = false;
        TodoBaseDTO todoBaseDTO = new TodoBaseDTO(title, description, completed);

        // Vérification des valeurs des champs
        assertEquals(title, todoBaseDTO.getTitle());
        assertEquals(description, todoBaseDTO.getDescription());
        assertEquals(completed, todoBaseDTO.isCompleted());

        // Vous pouvez également effectuer d'autres vérifications si nécessaire.
    }

    @Test
    void testDataAnnotation() {
        // Créez une instance de TodoBaseDTO pour les tests
        TodoBaseDTO todoDTO = TodoBaseDTO.builder()
                .title("Todo Title")
                .description("Todo Description")
                .completed(false)
                .build();

        // Créez une autre instance de TodoBaseDTO avec les mêmes valeurs pour les tests d'égalité
        TodoBaseDTO anotherTodoDTO = TodoBaseDTO.builder()
                .title("Todo Title")
                .description("Todo Description")
                .completed(false)
                .build();

        // Créez une troisième instance de TodoBaseDTO avec des valeurs différentes
        TodoBaseDTO thirdTodoDTO = TodoBaseDTO.builder()
                .title("Another Todo")
                .description("Another Description")
                .completed(true)
                .build();

        // Testez les conditions d'égalité
        assertTrue(todoDTO.equals(todoDTO));
        assertTrue(todoDTO.equals(anotherTodoDTO) && anotherTodoDTO.equals(todoDTO));
        assertTrue(todoDTO.hashCode() == anotherTodoDTO.hashCode());
        assertTrue(todoDTO.toString().equals(anotherTodoDTO.toString()));
        assertTrue(todoDTO.getTitle().equals(anotherTodoDTO.getTitle()));
        assertTrue(todoDTO.getDescription().equals(anotherTodoDTO.getDescription()));
        assertTrue(todoDTO.isCompleted() == anotherTodoDTO.isCompleted());
        assertFalse(todoDTO.equals(null));

        // Testez avec le troisième objet pour vérifier la non-égalité
        assertFalse(todoDTO.equals(thirdTodoDTO));
        assertFalse(todoDTO.hashCode() == thirdTodoDTO.hashCode());
        assertFalse(todoDTO.toString().equals(thirdTodoDTO.toString()));
    }



    @Test
    void testGettersAndSetters() {
        TodoBaseDTO todoDTO = new TodoBaseDTO();

        // Utilisez les setters pour définir les valeurs
        todoDTO.setTitle("Todo Title");
        todoDTO.setDescription("Todo Description");
        todoDTO.setCompleted(true);

        // Utilisez les getters pour obtenir les valeurs
        assertEquals("Todo Title", todoDTO.getTitle());
        assertEquals("Todo Description", todoDTO.getDescription());
        assertTrue(todoDTO.isCompleted());

        // Modifiez les valeurs en utilisant les setters
        todoDTO.setTitle("New Todo Title");
        todoDTO.setDescription("New Todo Description");
        todoDTO.setCompleted(false);

        // Vérifiez les nouvelles valeurs en utilisant les getters
        assertEquals("New Todo Title", todoDTO.getTitle());
        assertEquals("New Todo Description", todoDTO.getDescription());
        assertFalse(todoDTO.isCompleted());
    }

    @Test
    void testToString() {
        TodoBaseDTO todoDTO = TodoBaseDTO.builder()
                .title("Todo Title")
                .description("Todo Description")
                .completed(true)
                .build();

        // Vérifiez que la méthode toString génère la représentation attendue
        String expectedToString = "TodoBaseDTO(title=Todo Title, description=Todo Description, completed=true)";
        assertEquals(expectedToString, todoDTO.toString());
    }

}
