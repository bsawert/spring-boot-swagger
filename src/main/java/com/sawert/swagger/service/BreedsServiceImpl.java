package com.sawert.swagger.service;

import com.sawert.swagger.repository.BreedMapper;
import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.BreedRepository;
import com.sawert.swagger.service.BreedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedsServiceImpl implements BreedsService {

    @Autowired
    private BreedRepository breedRepository;

    @Override
    public Breed addBreed(Breed breed) {
        return null;
    }

    @Override
    public List<Breed> getBreeds() {
        List<Breed> breeds = new ArrayList<>();
        breedRepository.findAll().forEach(dto -> breeds.add(BreedMapper.toBreed(dto)));

        return breeds;
    }

    @Override
    public Breed getBreed(Long id) {
        return null;
    }

    @Override
    public Breed getBreedByName(String name) {
        return null;
    }
}
