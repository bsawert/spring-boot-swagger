package com.sawert.swagger.repository;

import com.google.common.collect.Sets;
import com.sawert.swagger.model.AKCGroup;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class BreedRepositoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BreedRepository repository;

    @Test
    public void testAddBreed() {
        BreedDto dto = new BreedDto("Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS);
        BreedDto breedDto = this.repository.save(dto);
        assertNotNull(breedDto);
        assertNotNull(breedDto.getId());
    }

    @Test
    public void testGetBreed() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Test Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        Optional<BreedDto> breedDto = this.repository.findById(dto.getId());
        assertTrue(breedDto.isPresent());
    }

    @Test
    public void testGetBreedNotFound() {
        Optional<BreedDto> breedDto = this.repository.findById(42L);
        assertFalse(breedDto.isPresent());
    }

    @Test
    public void testFindByName() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Test Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        List<BreedDto> breedDtos = this.repository.findByName(dto.getName());
        assertNotNull(breedDtos);
        assertEquals(1, breedDtos.size());
        assertEquals(dto, breedDtos.get(0));
    }

    @Test
    public void testFindByNameNotFound() {
        List<BreedDto> breedDtos = this.repository.findByName("NoName");
        assertNotNull(breedDtos);
        assertEquals(0, breedDtos.size());
    }

    @Test
    public void testFindByNameIgnoreCase() {
        BreedDto dto = this.entityManager.persistFlushFind(
            new BreedDto("Test Mutt", "Pound puppy", AKCGroup.MISCELLANEOUS));
        List<BreedDto> breedDtos = this.repository.findByNameIgnoreCase(dto.getName().toLowerCase());
        assertNotNull(breedDtos);
        assertEquals(1, breedDtos.size());
        assertEquals(dto, breedDtos.get(0));
    }

    @Test
    public void testFindBreedsByAKCGroup() {
        List<BreedDto> breedDtos = this.repository.findByAkcgroupIn(
                Sets.newHashSet(AKCGroup.TOY, AKCGroup.HOUND)
        );
        assertNotNull(breedDtos);
        assertEquals(3, breedDtos.size());
    }

    @Test
    public void testFindBreedsByAllAKCGroups() {
        Map<AKCGroup, List<BreedDto>> groupBreedDtoMap = new HashMap<>();
        Map<AKCGroup, List<BreedDto>> allBreedDtos =
            StreamSupport.stream(this.repository.findAll().spliterator(), false)
            .collect(Collectors.groupingBy(BreedDto::getAkcgroup));
        assertNotNull(allBreedDtos);
        assertEquals(4, allBreedDtos.size());

        Arrays.stream(AKCGroup.values()).forEach(key -> {
            groupBreedDtoMap.merge(key, allBreedDtos.getOrDefault(key, Collections.emptyList()), (first, second) -> {
                first.addAll(second);
                return first;
            });
        });

        assertNotNull(groupBreedDtoMap);
        assertEquals(AKCGroup.values().length, groupBreedDtoMap.size());
    }
}
