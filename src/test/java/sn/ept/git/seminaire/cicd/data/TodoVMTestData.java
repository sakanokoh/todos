package sn.ept.git.seminaire.cicd.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.dto.vm.TodoVM;

public final class TodoVMTestData extends TestData {

    public static TodoVM defaultVM() {
        return TodoVM
                .builder()
                .id(Default.id)
                .createdDate(Default.createdDate)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .title(Default.title)
                .description(Default.description)
                .build();
    }

    public static TodoVM updatedVM() {
        return TodoVM
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .title(Update.title)
                .description(Update.description)
                .build();
    }

    

    @Test
    void testDataAnnotations() {
        // Création d'une instance de TodoVM à l'aide du constructeur généré par Lombok
        Set<UUID> tags = new HashSet<>();
        tags.add(UUID.randomUUID());
        tags.add(UUID.randomUUID());

        TodoVM todoVM = TodoVM.builder()
                .title("Sample Title")
                .description("Sample Description")
                .completed(true)
                .tags(tags)
                .build();

        // Vérification des valeurs des champs
        assertEquals("Sample Title", todoVM.getTitle());
        assertEquals("Sample Description", todoVM.getDescription());
        assertTrue(todoVM.isCompleted());
        assertEquals(tags, todoVM.getTags());

        // Vérification des méthodes générées par Lombok
        assertEquals(todoVM, todoVM); // Vérifie que equals() fonctionne
        assertEquals(todoVM.hashCode(), todoVM.hashCode()); // Vérifie que hashCode() fonctionne
        assertNotNull(todoVM.toString()); // Vérifie que toString() ne renvoie pas null

        // Vous pouvez également tester d'autres méthodes générées par Lombok si nécessaire.
    }
}
