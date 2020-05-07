package com.sawert.swagger.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import javax.persistence.PersistenceException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class BreedDtoTest {
    String name = "Mutt";
    String description = "Pound puppy";
    AKCGroup akcGroup = AKCGroup.MISCELLANEOUS;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createWhenNameIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new BreedDto(null, description, akcGroup);
    }

    @Test
    public void createWhenNameIsEmptyShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new BreedDto("", description, akcGroup);
    }

    @Test
    public void saveShouldPersistData() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto(name, description, akcGroup));
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getDescription()).isEqualTo(description);
    }

    @Test
    public void saveDuplicateNameShouldThrowException() {
        this.thrown.expect(PersistenceException.class);
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        this.entityManager.persistFlushFind(
            new BreedDto("Mutt", "Another Pound puppy", AKCGroup.HOUND));

        fail("Unique constraint on name failed");
    }

    @Test
    public void createWithBuilder() {
        BreedDto dto = new BreedDto(name, description, akcGroup);
        BreedDto dtoBuilt = BreedDto.builder()
            .name(name)
            .description(description)
            .akcgroup(akcGroup)
            .build();

        assertThat(dtoBuilt.getName()).isEqualTo(dto.getName());
        assertThat(dtoBuilt.getDescription()).isEqualTo(dto.getDescription());
        assertThat(dtoBuilt.getAkcgroup()).isEqualTo(dto.getAkcgroup());
    }

    @Test
    public void createFromBreed() {
        Breed breed = new Breed();
        breed.setName(name);
        breed.setDescription(description);
        breed.setAkcgroup(akcGroup);

        BreedDto dto = new BreedDto(breed);

        assertThat(dto.getName()).isEqualTo(breed.getName());
        assertThat(dto.getDescription()).isEqualTo(breed.getDescription());
        assertThat(dto.getAkcgroup()).isEqualTo(breed.getAkcgroup());
    }
}