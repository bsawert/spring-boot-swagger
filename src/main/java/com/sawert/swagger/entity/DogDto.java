package com.sawert.swagger.entity;

import com.sawert.swagger.model.Dog;
import com.sawert.swagger.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DogDto {

    @Id
    @SequenceGenerator(name = "dog_generator", sequenceName = "dog_sequence", initialValue = 100)
    @GeneratedValue(generator = "dog_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany()
    @JoinTable(name = "dog_breed",
        joinColumns = { @JoinColumn(name = "fk_dog") },
        inverseJoinColumns = { @JoinColumn(name = "fk_breed") })
    private Set<BreedDto> breedDtos;

    public DogDto(String name, String description, Gender gender, Set<BreedDto> breedDtos) {
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.breedDtos = breedDtos;
    }

    public DogDto(Dog dog) {
        this(dog.getId(), dog.getName(), dog.getDescription(), dog.getGender(),
            dog.getBreed().stream().map(breed -> new BreedDto(breed)).collect(Collectors.toSet()));
    }

    public Dog toDog() {
        return new Dog()
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .gender(this.gender)
            .breed(this.breedDtos.stream().map(breedDto -> breedDto.toBreed()).collect(Collectors.toList()));
    }
}
