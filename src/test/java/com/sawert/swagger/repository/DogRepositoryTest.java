package com.sawert.swagger.repository;

import com.sawert.swagger.model.Gender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DogRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DogRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testGetDog() {
        DogDto dto = this.entityManager.persistFlushFind(
                new DogDto("Fluffy", "Lap dog", Gender.FEMALE));
        DogDto dogDto = this.repository.findOne(dto.getId());
        assertNotNull(dogDto);
    }
}