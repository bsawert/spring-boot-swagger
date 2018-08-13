package com.sawert.swagger.repository;

import com.sawert.swagger.model.AKCGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface BreedRepository extends CrudRepository<BreedDto, Long> {
    public List<BreedDto> findByName(String name);
    public List<BreedDto> findByNameIgnoreCase(String name);
    public List<BreedDto> findBreedDtosByAkcgroupIn(Set<AKCGroup> akcGroupSet);
}
