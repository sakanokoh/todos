package sn.ept.git.seminaire.cicd.utils;

import org.junit.jupiter.api.Test;
import sn.ept.git.seminaire.cicd.models.BaseEntity;
import sn.ept.git.seminaire.cicd.utils.Utils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    // Créez une classe fictive qui hérite de BaseEntity pour les tests
    static class MockEntity extends BaseEntity {
        MockEntity(UUID id) {
            super(id);
        }
    }

    @Test
    void contains_shouldReturnTrueWhenIdExists() {
        UUID id = UUID.randomUUID();
        Set<BaseEntity> entitySet = new HashSet<>();
        entitySet.add(new MockEntity(id));

        assertTrue(Utils.contains(entitySet, id));
    }

    @Test
    void contains_shouldReturnFalseWhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        Set<BaseEntity> entitySet = new HashSet<>();

        assertFalse(Utils.contains(entitySet, id));
    }

    @Test
    void findAbsentIds_shouldReturnEmptySetWhenAllIdsExist() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Set<BaseEntity> entitySet = new HashSet<>();
        entitySet.add(new MockEntity(id1));
        entitySet.add(new MockEntity(id2));

        Set<UUID> ids = new HashSet<>(Arrays.asList(id1, id2));

        Set<UUID> absentIds = Utils.findAbsentIds(entitySet, ids);

        assertTrue(absentIds.isEmpty());
    }

    @Test
    void findAbsentIds_shouldReturnSetOfIdsThatDoNotExist() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        Set<BaseEntity> entitySet = new HashSet<>();
        entitySet.add(new MockEntity(id1));

        Set<UUID> ids = new HashSet<>(Arrays.asList(id1, id2, id3));

        Set<UUID> absentIds = Utils.findAbsentIds(entitySet, ids);

        assertEquals(2, absentIds.size());
        assertTrue(absentIds.contains(id2));
        assertTrue(absentIds.contains(id3));
    }

    @Test
    public void testFindAbsentIds() {
        // Créez une liste de BaseEntity simulée
        List<BaseEntity> entityList = new ArrayList<>();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        entityList.add(new MockEntity(id1));
        entityList.add(new MockEntity(id2));

        // Créez un ensemble d'UUID simulé
        Set<UUID> idSet = new HashSet<>();
        idSet.add(id1);
        idSet.add(id2);
        idSet.add(id3);

        // Appelez la méthode findAbsentIds
        Set<UUID> result = Utils.findAbsentIds(entityList, idSet);

        // Vérifiez que le résultat contient uniquement id3
        assertEquals(1, result.size());
        assertEquals(id3, result.iterator().next());
    }
}
