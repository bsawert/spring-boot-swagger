package com.sawert.swagger.impl;

import com.sawert.swagger.entity.DogMapper;
import com.sawert.swagger.model.Dog;
import com.sawert.swagger.repository.DogRepository;
import com.sawert.swagger.service.DogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogsServiceImpl implements DogsService {

    @Autowired
    private DogRepository dogRepository;

    @Override
    public Dog addDog(Dog dog) {
        return null;
    }

    @Override
    public List<Dog> getDogs() {
        List<Dog> dogs = new ArrayList<>();
        dogRepository.findAll().forEach(dto -> dogs.add(DogMapper.toDog(dto)));

        return dogs;
    }

    @Override
    public Dog getDog(Long id) {
        return null;
    }

    @Override
    public Dog getDogByName(String name) {
        return null;
    }
}
