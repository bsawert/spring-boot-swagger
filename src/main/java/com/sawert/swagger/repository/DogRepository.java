package com.sawert.swagger.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<DogDto, Long> {
    public List<DogDto> findByName(String name);
    public List<DogDto> findByNameIgnoreCase(String name);
}
