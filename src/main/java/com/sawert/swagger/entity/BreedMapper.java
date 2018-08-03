package com.sawert.swagger.entity;

import com.sawert.swagger.model.Breed;

public class BreedMapper {

    public static Breed toBreed(BreedDto breedDto) {
        return new Breed()
            .id(breedDto.getId())
            .name(breedDto.getName())
            .description(breedDto.getDescription())
            .akcgroup(breedDto.getAkcgroup());
    }

    public static BreedDto toBreedDto(Breed breed) {
        BreedDto breedDto = new BreedDto();
        breedDto.setId(breed.getId());
        breedDto.setName(breed.getName());
        breedDto.setDescription(breed.getDescription());
        breedDto.setAkcgroup(breed.getAkcgroup());

        return breedDto;
    }
}
