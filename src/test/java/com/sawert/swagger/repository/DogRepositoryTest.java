package com.sawert.swagger.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Gender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DogRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DogRepository repository;

    @Test
    public void testGetDog() {
        DogDto dto = this.entityManager.persistFlushFind(
            new DogDto("Fluffy", "Lap dog", Gender.FEMALE));
        DogDto dogDto = this.repository.findOne(dto.getId());
        assertNotNull(dogDto);
    }

    @Test
    public void testGetDogNotFound() {
        DogDto dogDto = this.repository.findOne(42L);
        assertNull(dogDto);
    }

    @Test
    public void testFindByName() {
        DogDto dto = this.entityManager.persistFlushFind(
            new DogDto("Fluffy", "Lap dog", Gender.FEMALE));
        List<DogDto> dogDtos = this.repository.findByName(dto.getName());
        assertNotNull(dogDtos);
        assertEquals(1, dogDtos.size());
        assertEquals(dto, dogDtos.get(0));
    }

    @Test
    public void testFindByNameNotFound() {
        List<DogDto> dogDtos = this.repository.findByName("NoName");
        assertNotNull(dogDtos);
        assertEquals(0, dogDtos.size());
    }

    @Test
    public void testFindByNameIgnoreCase() {
        DogDto dto = this.entityManager.persistFlushFind(
            new DogDto("Fluffy", "Lap dog", Gender.FEMALE));
        List<DogDto> dogDtos = this.repository.findByNameIgnoreCase(dto.getName());
        assertNotNull(dogDtos);
        assertEquals(1, dogDtos.size());
        assertEquals(dto, dogDtos.get(0));;
    }

    @Test
    public void testFindByBreed() {
        BreedDto breedDto1 = this.entityManager.find(BreedDto.class, 1L);
        List<DogDto> dogDtos = this.repository.findByBreedDtos(Sets.newHashSet(breedDto1));
        assertNotNull(dogDtos);
        assertEquals(1, dogDtos.size());
        assertEquals(new Long(1), dogDtos.get(0).getId());;
    }

    @Test
    public void testFindByBreedIn() {
        BreedDto breedDto1 = this.entityManager.find(BreedDto.class, 1L);
        List<DogDto> dogDtos = this.repository.findByBreedDtosIn(Sets.newHashSet(breedDto1));
        assertNotNull(dogDtos);
        assertEquals(1, dogDtos.size());
        assertEquals(new Long(1), dogDtos.get(0).getId());;
    }

    @Test
    public void testFindByNameAndBreed() {
        DogDto dogDto1 = this.entityManager.find(DogDto.class, 1L);
        Set<BreedDto> breedDtos = dogDto1.getBreedDtos();
        List<DogDto> dogDtos = this.repository.findDistinctByNameAndBreedDtosIn(
            dogDto1.getName(), dogDto1.getBreedDtos());
        assertNotNull(dogDtos);
        assertEquals(1, dogDtos.size());
        assertEquals(new Long(1), dogDtos.get(0).getId());;
    }
}