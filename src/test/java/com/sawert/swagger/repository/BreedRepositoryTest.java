package com.sawert.swagger.repository;

import com.sawert.swagger.model.AKCGroup;
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
public class BreedRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BreedRepository repository;

    @Test
    public void testGetBreed() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        BreedDto breedDto = this.repository.findOne(dto.getId());
        assertNotNull(breedDto);
    }

    @Test
    public void testFindByName() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        List<BreedDto> breedDtos = this.repository.findByName(dto.getName());
        assertNotNull(breedDtos);
        assertEquals(1, breedDtos.size());
        assertEquals(dto, breedDtos.get(0));
    }

    @Test
    public void testFindByNameIgnoreCase() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        List<BreedDto> breedDtos = this.repository.findByNameIgnoreCase(dto.getName().toLowerCase());
        assertNotNull(breedDtos);
        assertEquals(1, breedDtos.size());
        assertEquals(dto, breedDtos.get(0));
    }
}