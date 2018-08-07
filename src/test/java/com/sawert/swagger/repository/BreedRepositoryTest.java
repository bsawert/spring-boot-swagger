package com.sawert.swagger.repository;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BreedRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BreedRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetBreed() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        BreedDto breedDto = this.repository.findOne(dto.getId());
        assertNotNull(breedDto);
    }

}