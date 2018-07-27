package com.sawert.swagger.entity;

import com.sawert.swagger.model.Dog;
import com.sawert.swagger.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class DogDto {

    @Id
    @SequenceGenerator(name = "dog_generator", sequenceName = "dog_sequence", initialValue = 100)
    @GeneratedValue(generator = "dog_generator")
    private Long id;

    @Column(nullable = false)
    private final String name;

    private final String description;

    @Enumerated(EnumType.STRING)
    private final Gender gender;

    @ManyToOne
    private final BreedDto breedDto;

    public DogDto(Dog dog) {
        this(dog.getId(), dog.getName(), dog.getDescription(), dog.getGender(), new BreedDto(dog.getBreed()));
    }

    public Dog toDog() {
        return new Dog()
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .gender(this.gender)
            .breed(this.breedDto.toBreed());
    }
}
