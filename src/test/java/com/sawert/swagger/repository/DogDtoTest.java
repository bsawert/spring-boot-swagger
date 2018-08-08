package com.sawert.swagger.repository;

import com.sawert.swagger.model.Gender;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DogDtoTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createWhenNameIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new DogDto(null, "Lap dog", Gender.FEMALE, Collections.<BreedDto>emptySet());
    }

    @Test
    public void createWhenNameIsEmptyShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new DogDto("", "Lap dog", Gender.FEMALE, Collections.<BreedDto>emptySet());
    }

    @Test
    public void saveShouldPersistData() {
        DogDto dto = this.entityManager.persistFlushFind(
            new DogDto("Fluffy", "Lap dog", Gender.FEMALE, Collections.<BreedDto>emptySet()));
        assertThat(dto.getName()).isEqualTo("Fluffy");
        assertThat(dto.getDescription()).isEqualTo("Lap dog");
    }
}