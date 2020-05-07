package com.sawert.swagger.service;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.BreedDto;
import com.sawert.swagger.repository.BreedMapper;
import com.sawert.swagger.repository.BreedRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BreedsServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class BreedsServiceImplTestContextConfiguration {
        @Bean
        public BreedsService breedsService() {
            return new BreedsServiceImpl();
        }
    }

    @Autowired
    private BreedsService breedsService;

    @MockBean
    private BreedRepository breedRepository;

    private int numMockBreeds = 3;
    private List<BreedDto> breedDtoList;

    @Before
    public void setup() {
        setupMockRepository(this.numMockBreeds);
    }

    @Test
    public void testAddBreed() {
        Breed breed = new Breed()
            .name("Jack Russell")
            .description("Jack Russell terrier")
            .akcgroup(AKCGroup.TERRIER);
        this.breedsService.addBreed(breed);

        ArgumentCaptor<BreedDto> argumentCaptor = ArgumentCaptor.forClass(BreedDto.class);
        verify(this.breedRepository).save(argumentCaptor.capture());
        BreedDto breedDto = argumentCaptor.getValue();
        assertThat(breedDto.getName().equals(breed.getName()));
        assertThat(breedDto.getDescription().equals(breed.getDescription()));
        assertThat(breedDto.getAkcgroup().equals(breed.getAkcgroup()));
    }

    @Test
    public void testGetBreeds() {
        List<Breed> breedList = this.breedsService.getBreeds();
        assertThat(breedList).isNotNull();
        assertThat(breedList.size()).isEqualTo(this.numMockBreeds);
        assertThat(breedList.get(0)).isEqualTo(BreedMapper.toBreed(this.breedDtoList.get(0)));
    }

    @Test
    public void testGetBreedById() {
        int index = new Random().nextInt(this.numMockBreeds);
        Breed breed = this.breedsService.getBreed(1L + index);
        assertThat(breed).isNotNull();
        assertThat(breed.getId()).isEqualTo(1L + index);
        assertThat(breed.getName()).isEqualTo(this.breedDtoList.get(index).getName());
        assertThat(breed.getDescription()).isEqualTo(this.breedDtoList.get(index).getDescription());
        assertThat(breed.getAkcgroup()).isEqualTo(this.breedDtoList.get(index).getAkcgroup());
    }

    @Test
    public void testGetBreedByName() {
        int index = new Random().nextInt(this.numMockBreeds);
        String name = this.breedDtoList.get(index).getName();
        Breed breed = this.breedsService.getBreedByName(name);
        assertThat(breed).isNotNull();
        assertThat(breed.getId()).isEqualTo(1L + index);
        assertThat(breed.getName()).isEqualTo(this.breedDtoList.get(index).getName());
        assertThat(breed.getDescription()).isEqualTo(this.breedDtoList.get(index).getDescription());
        assertThat(breed.getAkcgroup()).isEqualTo(this.breedDtoList.get(index).getAkcgroup());
    }

    @Test
    public void testGetBreedsByAkcGroup() {
    }

    @Test
    public void testGetBreedsByAkcGroups() {
    }

    private void setupMockRepository(int numBreeds) {
        this.breedDtoList = new ArrayList();
        for (int index = 1; index <= numBreeds; index++) {
            BreedDto breedDto = new BreedDto("Breed " + index, "Breed " + index, AKCGroup.values()[index - 1]);
            breedDto.setId(0L + index);
            this.breedDtoList.add(breedDto);
        }

        when(this.breedRepository.findAll()).thenReturn(this.breedDtoList);

        Answer<Optional<BreedDto>> idAnswer = new Answer<Optional<BreedDto>>() {
            public Optional<BreedDto> answer(InvocationOnMock invocation) throws Throwable {
                Long id = invocation.getArgument(0);
                return Optional.<BreedDto>ofNullable(
                    BreedsServiceImplTest.this.breedDtoList
                        .stream()
                        .filter(dto -> dto.getId().equals(id))
                        .findFirst()
                        .orElse(null)
                );
            }
        };

        when(this.breedRepository.findById(anyLong())).thenAnswer(idAnswer);

        Answer<List<BreedDto>> nameAnswer = new Answer<List<BreedDto>>() {
            public List<BreedDto> answer(InvocationOnMock invocation) throws Throwable {
                String name = invocation.getArgument(0);
                return BreedsServiceImplTest.this.breedDtoList
                        .stream()
                        .filter(dto -> dto.getName().equals(name))
                        .collect(Collectors.toList());
            }
        };

        when(this.breedRepository.findByName(anyString())).thenAnswer(nameAnswer);
    }
}