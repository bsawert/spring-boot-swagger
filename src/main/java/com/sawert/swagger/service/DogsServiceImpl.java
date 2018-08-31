package com.sawert.swagger.service;

import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.*;
import com.sawert.swagger.model.Dog;
import com.sawert.swagger.service.DogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Dog dog = null;
        DogDto dto = this.dogRepository.findOne(id);
        if (dto != null) {
            dog = DogMapper.toDog(dto);
        }

        return dog;
    }

    @Override
    public List<Dog> getDogByName(String name) {
        List<Dog> dogs = new ArrayList<Dog>();
        List<DogDto> dtos = this.dogRepository.findByName(name);
        if (dtos != null && !dtos.isEmpty()) {
            dogs = dtos.stream().map(DogMapper::toDog).collect(Collectors.toList());
        }

        return dogs;
    }

    @Override
    public List<Dog> getDogsByBreed(Breed breed) {
        return null;
    }

    @Override
    public List<Dog> getDogsByBreeds(Set<Breed> breedSet) {
        return null;
    }
}
