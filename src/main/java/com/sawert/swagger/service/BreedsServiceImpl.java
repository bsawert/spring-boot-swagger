package com.sawert.swagger.service;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.BreedDto;
import com.sawert.swagger.repository.BreedMapper;
import com.sawert.swagger.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BreedsServiceImpl implements BreedsService {

    @Autowired
    private BreedRepository breedRepository;

    @Override
    public Breed addBreed(Breed breed) {
        Breed newBreed = null;
        BreedDto dto = new BreedDto(breed);
        BreedDto newDto = this.breedRepository.save(dto);
        if (newDto != null) {
            newBreed = BreedMapper.toBreed(newDto);
        }

        return newBreed;
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
        Optional<BreedDto> dto = this.breedRepository.findById(id);
        if (dto.isPresent()) {
            breed = BreedMapper.toBreed(dto.get());
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

    @Override
    public List<Breed> getBreedsByAkcGroup(AKCGroup akcGroup) {
        return getBreedsByAkcGroups(Collections.singleton(akcGroup));
    };

    @Override
    public List<Breed> getBreedsByAkcGroups(Set<AKCGroup> akcGroupSet) {
        List<Breed> breeds = new ArrayList<>();
        List<BreedDto> dtos = this.breedRepository.findByAkcgroupIn(akcGroupSet);
        if (dtos != null && !dtos.isEmpty()) {
            dtos.forEach(dto -> breeds.add(BreedMapper.toBreed(dto)));
        }

        return breeds;
    };
}
