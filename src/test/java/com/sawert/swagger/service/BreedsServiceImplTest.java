package com.sawert.swagger.service;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.BreedDto;
import com.sawert.swagger.repository.BreedRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class BreedsServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class BreedsServiceImplTestContextConfiguration {

        @Bean
        public BreedsService breedsService() {
            return new BreedsServiceImpl();
        }
    }

    @Autowired
    private BreedsService breedsService;

    @MockBean
    private BreedRepository breedRepository;

    @Test
    public void testAddBreed() {
        Breed breed = new Breed()
            .name("Jack Russell")
            .description("Jack Russell terrier")
            .akcgroup(AKCGroup.TERRIER);
        this.breedsService.addBreed(breed);

        ArgumentCaptor<BreedDto> argumentCaptor = ArgumentCaptor.forClass(BreedDto.class);
        verify(breedRepository).save(argumentCaptor.capture());
        BreedDto breedDto = argumentCaptor.getValue();
        assertThat(breedDto.getName().equals(breed.getName()));
        assertThat(breedDto.getDescription().equals(breed.getDescription()));
        assertThat(breedDto.getAkcgroup().equals(breed.getAkcgroup()));
    }

    @Test
    public void testGetBreeds() {
    }

    @Test
    public void testGetBreed() {
    }

    @Test
    public void testGetBreedByName() {
    }

    @Test
    public void testGetBreedsByAkcGroup() {
    }

    @Test
    public void testGetBreedsByAkcGroups() {
    }
}