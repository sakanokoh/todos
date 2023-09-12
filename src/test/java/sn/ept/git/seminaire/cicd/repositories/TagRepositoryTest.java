package sn.ept.git.seminaire.cicd.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.TagDTOTestData;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.mappers.TagMapper;
import sn.ept.git.seminaire.cicd.models.Tag;

import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class TagRepositoryTest extends RepositoryBaseTest {

    @Autowired
    private TagMapper mapper;
    @Autowired
    private TagRepository repository;

    static TagDTO dto;
    Tag entity;
    Optional<Tag> optionalTag;

    @BeforeAll
    static void beforeAll(){
        dto = TagDTOTestData.defaultDTO();
    }

    @BeforeEach
    void setUp() {
        entity = mapper.asEntity(dto);
        repository.deleteAll();
        entity = repository.saveAndFlush(entity);
    }

    @Test
    void findByName_shouldReturnResult() {
        optionalTag = repository.findByName(entity.getName());
        assertThat(optionalTag)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByName_withBadName_shouldReturnNotFound() {
        optionalTag = repository.findByName(UUID.randomUUID().toString());
        assertThat(optionalTag)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void findByName_afterDelete_shouldReturnNotFound() {
        entity.setDeleted(true);
        entity = repository.saveAndFlush(entity);
        optionalTag = repository.findByName(entity.getName());
        assertThat(optionalTag)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void findByNameWithIdNotEqual_shouldReturnResult() {
        optionalTag = repository.findByNameWithIdNotEquals(entity.getName(),UUID.randomUUID());
        assertThat(optionalTag)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void  findByNameWithIdNotEqual_withSameId_shouldReturnNoResult () {
        optionalTag = repository.findByNameWithIdNotEquals(entity.getName(),entity.getId());
        assertThat(optionalTag)
                .isNotNull()
                .isNotPresent();
    }

    

    @Test
    void findByNameWithIdNotEqual_1_shouldReturnResult() {
        Tag otherTag = new Tag();
        otherTag.setName("DifferentName");
        otherTag = repository.saveAndFlush(otherTag);

        optionalTag = repository.findByNameWithIdNotEquals(otherTag.getName(), entity.getId());
        assertThat(optionalTag)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(otherTag);
    }

    @Test
    void updateTag_shouldUpdateEntity() {
        String updatedName = "UpdatedName";
        entity.setName(updatedName);
        optionalTag = repository.findByName(entity.getName());

        assertThat(optionalTag)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }


}