package com.sawert.swagger.service;

import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.BreedDto;
import com.sawert.swagger.repository.BreedMapper;
import com.sawert.swagger.repository.BreedRepository;
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
        this.breedRepository.findAll().forEach(dto -> breeds.add(BreedMapper.toBreed(dto)));

        return breeds;
    }

    @Override
    public Breed getBreed(Long id) {
        Breed breed = null;
        BreedDto dto = this.breedRepository.findOne(id);
        if (dto != null) {
            breed = BreedMapper.toBreed(dto);
        }

        return breed;
    }

    @Override
    public Breed getBreedByName(String name) {
        Breed breed = null;
        List<BreedDto> dtos = this.breedRepository.findByName(name);
        if (dtos != null && !dtos.isEmpty()) {
            breed = BreedMapper.toBreed(dtos.get(0));
        }

        return breed;
    }
}
