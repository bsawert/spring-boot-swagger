package com.sawert.swagger.repository;

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

import static org.junit.Assert.assertEquals;
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
    public void testFindByName() {
        DogDto dto = this.entityManager.persistFlushFind(
            new DogDto("Fluffy", "Lap dog", Gender.FEMALE));
        List<DogDto> dogDtos = this.repository.findByName(dto.getName());
        assertNotNull(dogDtos);
        assertEquals(1, dogDtos.size());
        assertEquals(dto, dogDtos.get(0));
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
}