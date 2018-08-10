package com.sawert.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface DogRepository extends CrudRepository<DogDto, Long> {
    public List<DogDto> findByName(String name);
    public List<DogDto> findByNameIgnoreCase(String name);
    public List<DogDto> findByBreedDtos(Set<BreedDto> breedDtoSet);
    public List<DogDto> findByBreedDtosIn(Set<BreedDto> breedDtoSet);
    public List<DogDto> findDistinctByNameAndBreedDtosIn(String name, Set<BreedDto> breedDtoSet);
}
