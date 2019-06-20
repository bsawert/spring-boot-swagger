package com.sawert.swagger.repository;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;
import com.sawert.swagger.model.Dog;
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
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DogDtoTest {
    String name = "Fluffy";
    String description = "Lap dog";
    Gender gender = Gender.FEMALE;
    Set<BreedDto> breedDtos = Collections.EMPTY_SET;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createWhenNameIsNullShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new DogDto(null, description, gender, breedDtos);
    }

    @Test
    public void createWhenNameIsEmptyShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Name must not be empty");
        new DogDto("",  description, gender, breedDtos);
    }

    @Test
    public void saveShouldPersistData() {
        DogDto dto = this.entityManager.persistFlushFind(
            new DogDto(name, description, gender, breedDtos));
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getDescription()).isEqualTo(description);
        assertThat(dto.getGender()).isEqualTo(gender);
        assertThat(dto.getBreedDtos()).isEqualTo(breedDtos);
    }

    @Test
    public void createWithPartialConstructor() {
        DogDto dto = new DogDto(name, description, gender, Collections.EMPTY_SET);
        DogDto dtoPartial = new DogDto(name, description, gender);

        assertThat(dtoPartial).isEqualTo(dto);
    }

    @Test
    public void createWithBuilder() {
        DogDto dto = new DogDto(name, description, gender, breedDtos);
        DogDto dtoBuilt = DogDto.builder()
                .name(name)
                .description(description)
                .gender(gender)
                .breedDtos(breedDtos)
                .build();

        assertThat(dtoBuilt.getName()).isEqualTo(dto.getName());
        assertThat(dtoBuilt.getDescription()).isEqualTo(dto.getDescription());
        assertThat(dtoBuilt.getGender()).isEqualTo(dto.getGender());
        assertThat(dtoBuilt.getBreedDtos()).isEqualTo(dto.getBreedDtos());
    }

    @Test
    public void createFromBreed() {
        Dog dog = new Dog();
        dog.setName(name);
        dog.setDescription(description);
        dog.setGender(gender);
        dog.setBreed(Collections.EMPTY_LIST);

        DogDto dto = new DogDto(dog);

        assertThat(dto.getName()).isEqualTo(dog.getName());
        assertThat(dto.getDescription()).isEqualTo(dog.getDescription());
        assertThat(dto.getGender()).isEqualTo(dog.getGender());
        assertThat(dto.getBreedDtos()).isEmpty();
    }
}