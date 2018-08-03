package com.sawert.swagger.entity;

import com.sawert.swagger.model.Breed;
import com.sawert.swagger.model.Dog;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DogMapper {

    public static Dog toDog(DogDto dogDto) {
        List<Breed> breedList = dogDto.getBreedDtos().stream().map(BreedMapper::toBreed).collect(Collectors.toList());
        return new Dog()
            .id(dogDto.getId())
            .name(dogDto.getName())
            .description(dogDto.getDescription())
            .gender(dogDto.getGender())
            .breed(breedList);
    }

    public static DogDto toDogDto(Dog dog) {
        Set<BreedDto> breedDtoSet = dog.getBreed().stream().map(BreedMapper::toBreedDto).collect(Collectors.toSet());
        DogDto dogDto = new DogDto();
        dogDto.setId(dog.getId());
        dogDto.setName(dog.getName());
        dogDto.setDescription(dog.getDescription());
        dogDto.setBreedDtos(breedDtoSet);

        return dogDto;
    }
}
