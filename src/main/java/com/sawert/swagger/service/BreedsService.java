package com.sawert.swagger.service;

import com.sawert.swagger.model.Breed;

import java.util.List;

public interface BreedsService {
    public Breed addBreed(Breed breed);
    public List<Breed> getBreeds();
    public Breed getBreed(Long id);
    public Breed getBreedByName(String name);
}
