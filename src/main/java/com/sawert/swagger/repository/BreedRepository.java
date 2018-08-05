package com.sawert.swagger.repository;

import com.sawert.swagger.entity.BreedDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BreedRepository extends CrudRepository<BreedDto, Long> {
    public List<BreedDto> findByName(String name);
    public List<BreedDto> findByNameIgnoreCase(String name);
}
