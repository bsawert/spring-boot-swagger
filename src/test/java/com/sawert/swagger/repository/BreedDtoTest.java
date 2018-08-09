package com.sawert.swagger.repository;

import com.sawert.swagger.model.AKCGroup;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BreedDtoTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createWhenNameIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new BreedDto(null, "Pound puppy", AKCGroup.MISCELLANEOUS);
    }

    @Test
    public void createWhenNameIsEmptyShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new BreedDto("", "Pound puppy", AKCGroup.MISCELLANEOUS);
    }

    @Test
    public void saveShouldPersistData() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        assertThat(dto.getName()).isEqualTo("Mutt");
        assertThat(dto.getDescription()).isEqualTo("Pound puppy");
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
}