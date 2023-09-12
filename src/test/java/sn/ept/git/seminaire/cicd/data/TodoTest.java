package sn.ept.git.seminaire.cicd.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import sn.ept.git.seminaire.cicd.models.Tag;
import sn.ept.git.seminaire.cicd.models.Todo;

@DataJpaTest
public class TodoTest {



    @Test
    void testAllArgsConstructor() {
        // Création d'une instance de Todo à l'aide du constructeur généré par Lombok
        UUID id = UUID.randomUUID();
        String title = "Sample Title";
        String description = "Sample Description";
        boolean completed = false;
        Set<Tag> tags = new HashSet<>(); // Vous pouvez ajouter des Tags si nécessaire

        Todo todo = new Todo(title, description, completed, tags);

        // Vérification des valeurs des champs
        
        assertEquals(title, todo.getTitle());
        assertEquals(description, todo.getDescription());
        assertEquals(completed, todo.isCompleted());
        assertEquals(tags, todo.getTags());

        // Vous pouvez également effectuer d'autres vérifications si nécessaire.
    }

@Test
    void testUpdateWith() {
        // Créez deux instances de Todo, l'une pour la mise à jour et l'autre pour la mise à jour
        Todo originalTodo = Todo.builder()
                .id(UUID.randomUUID())
                .title("Original Title")
                .description("Original Description")
                .completed(false)
                .build();

        Todo updatedTodo = Todo.builder()
                .title("Updated Title")
                .description("Updated Description")
                .completed(true)
                .build();

        // Appelez la méthode updateWith pour mettre à jour l'originalTodo avec les valeurs de updatedTodo
        Todo resultTodo = originalTodo.updateWith(updatedTodo);

        // Vérifiez que resultTodo a été mis à jour correctement
        assertNotNull(resultTodo);
        assertEquals(originalTodo.getId(), resultTodo.getId());
        assertEquals(updatedTodo.getTitle(), resultTodo.getTitle());
        assertEquals(updatedTodo.getDescription(), resultTodo.getDescription());
        assertTrue(resultTodo.isCompleted());    }
 

    @Test
    void testDataAnnotation() {
        // Créez une instance de Todo pour les tests
        Todo todo = Todo.builder()
                .title("Complete Assignment")
                .description("Finish the homework assignment")
                .completed(false)
                .build();

        // Créez une autre instance de Todo avec les mêmes valeurs pour les tests d'égalité
        Todo anotherTodo = Todo.builder()
                .title("Complete Assignment")
                .description("Finish the homework assignment")
                .completed(false)
                .build();

        // Créez une troisième instance de Todo avec des valeurs différentes
        Todo thirdTodo = Todo.builder()
                .title("Go Shopping")
                .description("Buy groceries")
                .completed(true)
                .build();

        // Testez les conditions d'égalité
        assertTrue(todo.equals(todo));
        assertTrue(todo.equals(anotherTodo) && anotherTodo.equals(todo));
        assertTrue(todo.hashCode() == anotherTodo.hashCode());
        assertTrue(todo.toString().equals(anotherTodo.toString()));
        assertTrue(todo.getTitle().equals(anotherTodo.getTitle()));
        assertTrue(todo.getDescription().equals(anotherTodo.getDescription()));
        assertTrue(todo.isCompleted() == anotherTodo.isCompleted());
        assertFalse(todo.equals(null));

        // Testez avec le troisième objet pour vérifier la non-égalité
        assertFalse(todo.equals(thirdTodo));
        assertFalse(todo.hashCode() == thirdTodo.hashCode());
        assertFalse(todo.toString().equals(thirdTodo.toString()));
    }
}