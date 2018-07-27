package com.sawert.swagger.repository;

import com.sawert.swagger.entity.DogDto;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<DogDto, Long> {
}
