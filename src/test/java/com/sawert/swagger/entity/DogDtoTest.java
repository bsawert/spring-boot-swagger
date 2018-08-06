package com.sawert.swagger.entity;

import com.sawert.swagger.model.Gender;
import org.junit.After;
import org.junit.Before;
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
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveShouldPersistData() {
        DogDto dto = this.entityManager.persistFlushFind(
            new DogDto("Fluffy", "Lap dog", Gender.FEMALE, Collections.<BreedDto>emptySet()));
        assertThat(dto.getName()).isEqualTo("Fluffy");
        assertThat(dto.getDescription()).isEqualTo("Lap dog");
    }
}