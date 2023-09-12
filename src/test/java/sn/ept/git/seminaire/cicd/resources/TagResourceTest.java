package sn.ept.git.seminaire.cicd.resources;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import sn.ept.git.seminaire.cicd.data.TagDTOTestData;
import sn.ept.git.seminaire.cicd.data.TagVMTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.dto.TagDTO;
import sn.ept.git.seminaire.cicd.dto.vm.TagVM;
import sn.ept.git.seminaire.cicd.services.ITagService;
import sn.ept.git.seminaire.cicd.utils.SizeMapping;
import sn.ept.git.seminaire.cicd.utils.TestUtil;
import sn.ept.git.seminaire.cicd.utils.UrlMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@Slf4j
class TagResourceTest extends BasicResourceTest {

    @Autowired
    private ITagService service;
    private TagDTO dto;
     private TagVM vm;


    @BeforeAll
    static void beforeAll() {
        log.info(" before all ");
    }

    @BeforeEach
    void beforeEach() {
        log.info(" before each ");
        service.deleteAll();
        vm = TagVMTestData.defaultVM();
        dto = TagDTOTestData.defaultDTO();

    }

    @Test
    void findAll_shouldReturnTags() throws Exception {
        dto = service.save(vm);

        mockMvc.perform(get(UrlMapping.Tag.ALL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content.[0].id").exists())
                .andExpect(jsonPath("$.content.[0].version").exists())
                .andExpect(jsonPath("$.content.[0].enabled").exists())
                .andExpect(jsonPath("$.content.[0].deleted").exists())
                .andExpect(jsonPath("$.content.[0].enabled", is(true)))
                .andExpect(jsonPath("$.content.[0].deleted").value(false))
                .andExpect(jsonPath("$.content.[0].name", is(dto.getName())))
                .andExpect(jsonPath("$.content.[0].description").value(dto.getDescription()))
        ;



    }


    @Test
    void findById_shouldReturnTag() throws Exception {
        dto = service.save(vm);

        mockMvc.perform(get(UrlMapping.Tag.FIND_BY_ID, dto.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name", is(dto.getName())))
                .andExpect(jsonPath("$.description").value(dto.getDescription()))
        ;
    }

    @Test
    void findById_withBadId_shouldReturnNotFound() throws Exception {
        UUID id =UUID.randomUUID();
        mockMvc.perform(get(UrlMapping.Tag.FIND_BY_ID, id.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void add_shouldCreateTag() throws Exception {
        mockMvc.perform(
                        post(UrlMapping.Tag.ADD)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.description").value(vm.getDescription()))
        ;
    }

    @Test
    void add_withTitleMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));

        mockMvc.perform(post(UrlMapping.Tag.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_withTitleMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(post(UrlMapping.Tag.ADD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }



    @Test
    void update_shouldUpdateTag() throws Exception {
        dto = service.save(vm);
        vm.setName(TestData.Update.name);
        vm.setDescription(TestData.Update.description);
        mockMvc.perform(
                        put(UrlMapping.Tag.UPDATE, dto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(vm))
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.version").exists())
                .andExpect(jsonPath("$.enabled").exists())
                .andExpect(jsonPath("$.deleted").exists())
                .andExpect(jsonPath("$.name").value(vm.getName()))
                .andExpect(jsonPath("$.description").value(vm.getDescription()))
        ;
    }

    @Test
    void update_withTitleMinLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
        mockMvc.perform(put(UrlMapping.Tag.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_withTitleMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
        dto = service.save(vm);
        vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
        mockMvc.perform(put(UrlMapping.Tag.UPDATE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(vm)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void delete_shouldDeleteTag() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Tag.DELETE, dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    void delete_withBadId_shouldReturnNotFound() throws Exception {
        dto = service.save(vm);
        mockMvc.perform(
                delete(UrlMapping.Tag.DELETE, UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    void add_withDescriptionMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
    vm.setDescription(RandomStringUtils.random(SizeMapping.Description.MAX + 1));
    mockMvc.perform(post(UrlMapping.Tag.ADD)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isBadRequest());
}

@Test
void update_withDescriptionMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
    dto = service.save(vm);
    vm.setDescription(RandomStringUtils.random(SizeMapping.Description.MAX + 1));
    mockMvc.perform(put(UrlMapping.Tag.UPDATE, dto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isBadRequest());
}

@Test
void update_withNonExistingTagId_shouldReturnNotFound() throws Exception {
    mockMvc.perform(put(UrlMapping.Tag.UPDATE, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isNotFound());
}

@Test
void add_withNameMinLengthExceeded_shouldReturnBadRequest() throws Exception {
    vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
    
    mockMvc.perform(post(UrlMapping.Tag.ADD)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isBadRequest());
}

@Test
void add_withNameMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
    vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
    
    mockMvc.perform(post(UrlMapping.Tag.ADD)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isBadRequest());
}

@Test
void update_withNameMinLengthExceeded_shouldReturnBadRequest() throws Exception {
    dto = service.save(vm);
    vm.setName(RandomStringUtils.random(SizeMapping.Name.MIN - 1));
    
    mockMvc.perform(put(UrlMapping.Tag.UPDATE, dto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isBadRequest());
}

@Test
void update_withNameMaxLengthExceeded_shouldReturnBadRequest() throws Exception {
    dto = service.save(vm);
    vm.setName(RandomStringUtils.random(SizeMapping.Name.MAX + 1));
    
    mockMvc.perform(put(UrlMapping.Tag.UPDATE, dto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vm)))
            .andExpect(status().isBadRequest());
}

@Test
void addAll_shouldCreateTags() throws Exception {
    // Préparez des TagVM valides dans une liste
    List<TagVM> validTagVMs = new ArrayList<>();
    TagVM tagVM1 = new TagVM();
    tagVM1.setName("Tag1"); // Remplacez "Tag1" par un nom valide
    tagVM1.setDescription("Description du Tag1"); // Remplacez par une description valide
    validTagVMs.add(tagVM1);

    TagVM tagVM2 = new TagVM();
    tagVM2.setName("Tag2"); // Remplacez "Tag2" par un nom valide
    tagVM2.setDescription("Description du Tag2"); // Remplacez par une description valide
    validTagVMs.add(tagVM2);

    mockMvc.perform(
            post(UrlMapping.Tag.ADD_ALL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(validTagVMs))
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.length()").value(validTagVMs.size())) // Assurez-vous que le nombre de tags créés correspond à la taille de la liste d'entrée
        .andExpect(jsonPath("$.[0].id").exists()) // Vérifiez que les tags créés ont un ID
        // Ajoutez d'autres assertions pour vérifier que les données des tags créés correspondent aux TagVM d'origine
        // Assurez-vous également que les en-têtes de réponse appropriés sont renvoyés, le cas échéant
    ;
}





 

}