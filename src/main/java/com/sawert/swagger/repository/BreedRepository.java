package com.sawert.swagger.repository;

import com.sawert.swagger.entity.BreedDto;
import org.springframework.data.repository.CrudRepository;

public interface BreedRepository extends CrudRepository<BreedDto, Long> {
    public BreedDto findOneByName(String name);
}
