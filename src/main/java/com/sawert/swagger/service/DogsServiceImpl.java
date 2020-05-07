package com.sawert.swagger.service;

import com.sawert.swagger.model.Breed;
import com.sawert.swagger.model.Dog;
import com.sawert.swagger.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DogsServiceImpl implements DogsService {

    @Value("${test.config.colors}")
    private List<String> dogColors;

    @Value("#{ systemProperties['user.dir'] }")
    private String homeDir;

    @Value("#{ systemEnvironment }")
    private java.util.Properties systemEnvironment;

    @Autowired
    private DogRepository dogRepository;

    @Override
    public Dog addDog(Dog dog) {
        return null;
    }

    @Override
    public List<Dog> getDogs() {

        System.getenv("HOME");
        List<Dog> dogs = new ArrayList<>();
        dogRepository.findAll().forEach(dto -> dogs.add(DogMapper.toDog(dto)));

        return dogs;
    }

    @Override
    public Dog getDog(Long id) {
        Dog dog = null;
        Optional<DogDto> dto = this.dogRepository.findById(id);
        if (dto.isPresent()) {
            dog = DogMapper.toDog(dto.get());
        }

        return dog;
    }

    @Override
    public List<Dog> getDogByName(String name) {
        List<Dog> dogs = new ArrayList<Dog>();
        List<DogDto> dtos = this.dogRepository.findByName(name);
        if (dtos != null && !dtos.isEmpty()) {
            dogs = dtos.stream()
                .map(DogMapper::toDog)
                .collect(Collectors.toList());
        }

        return dogs;
    }

    @Override
    public List<Dog> getDogsByBreed(Breed breed) {
        return getDogsByBreeds(Collections.singleton(breed));
    }

    @Override
    public List<Dog> getDogsByBreeds(Set<Breed> breedSet) {
        List<Dog> dogs = new ArrayList<>();
        if (breedSet != null && !breedSet.isEmpty()) {
            Set<BreedDto> breedDtos = breedSet.stream()
                .map(BreedMapper::toBreedDto)
                .collect(Collectors.toSet());
            List<DogDto> dtos = this.dogRepository.findByBreedDtosIn(breedDtos);
            if (dtos != null && !dtos.isEmpty()) {
                dtos.forEach(dto -> dogs.add(DogMapper.toDog(dto)));
            }
        }

        return dogs;
    }
}
