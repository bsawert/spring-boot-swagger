package com.sawert.swagger.service;

import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedsServiceImpl implements BreedsService {

    @Autowired
    private BreedRepository breedRepository;

    public Breed addBreed(Breed breed) {
        return null;
    }

    public List<Breed> getBreeds() {
        List<Breed> breeds = new ArrayList<>();
        this.breedRepository.findAll().forEach(dto -> breeds.add(dto.toBreed()));
        return breeds;
    }

    public Breed getBreed(Long id) {
        return null;
    }

    public Breed getBreedByName(String name) {
        return null;
    }
}
