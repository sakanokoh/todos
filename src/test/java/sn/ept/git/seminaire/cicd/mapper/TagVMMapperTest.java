package sn.ept.git.seminaire.cicd.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sn.ept.git.seminaire.cicd.data.TagVMTestData;
import sn.ept.git.seminaire.cicd.dto.vm.TagVM;
import sn.ept.git.seminaire.cicd.mappers.vm.TagVMMapper;
import sn.ept.git.seminaire.cicd.models.Tag;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TagVMMapperTest {

    static TagVM vm;
    static Tag entity;
    static Tag entity1;
    static Tag entity2;
    static Tag entity3;

    private TagVMMapper mapper  =  Mappers.getMapper(TagVMMapper.class);

    @BeforeAll
    static void beforeAll() {
        vm = TagVMTestData.defaultVM();
    }

    @Test
    void toEntityShouldReturnCorrectEntity() {
        entity = mapper.asEntity(vm);
        assertThat(entity)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .isEqualTo(vm);
    }

    @Test
    void toDTOShouldReturnCorrectDTO() {
        vm = mapper.asDTO( mapper.asEntity(vm));
        assertThat(vm)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
}