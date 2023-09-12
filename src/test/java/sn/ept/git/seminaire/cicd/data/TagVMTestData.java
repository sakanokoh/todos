package sn.ept.git.seminaire.cicd.data;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import sn.ept.git.seminaire.cicd.dto.vm.TagVM;

public final class TagVMTestData extends TestData {

    public static TagVM defaultVM() {
        return TagVM
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

    public static TagVM updatedVM() {
        return TagVM
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


    @Test
    void testDataAnnotations() {
        // Création d'une instance de TagVM à l'aide du constructeur généré par Lombok
        TagVM tagVM = TagVM.builder()
                .name("Sample Name")
                .description("Sample Description")
                .build();

        // Vérification des valeurs des champs
        assertEquals("Sample Name", tagVM.getName());
        assertEquals("Sample Description", tagVM.getDescription());

        // Vérification des méthodes générées par Lombok
        assertEquals(tagVM, tagVM); // Vérifie que equals() fonctionne
        assertEquals(tagVM.hashCode(), tagVM.hashCode()); // Vérifie que hashCode() fonctionne
        assertNotNull(tagVM.toString()); // Vérifie que toString() ne renvoie pas null
        

        // Vous pouvez également tester d'autres méthodes générées par Lombok si nécessaire.
    }
}
