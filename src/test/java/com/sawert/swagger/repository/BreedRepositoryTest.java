package com.sawert.swagger.repository;

import com.google.common.collect.Sets;
import com.sawert.swagger.model.AKCGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
    public void testGetBreedNotFound() {
        BreedDto breedDto = this.repository.findOne(42L);
        assertNull(breedDto);
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
    public void testFindByNameNotFound() {
        List<BreedDto> breedDtos = this.repository.findByName("NoName");
        assertNotNull(breedDtos);
        assertEquals(0, breedDtos.size());
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

    @Test
    public void testFindBreedsByAKCGroup() {
        List<BreedDto> breedDtos = this.repository.findBreedDtosByAkcgroupIn(
                Sets.newHashSet(AKCGroup.TOY, AKCGroup.MISCELLANEOUS, AKCGroup.HOUND)
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
        assertEquals(2, allBreedDtos.size());

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
