package com.sawert.swagger.repository;

import com.sawert.swagger.model.Dog;
import com.sawert.swagger.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DogDto {

    @Id
    @SequenceGenerator(name = "dog_generator", sequenceName = "dog_sequence", initialValue = 100)
    @GeneratedValue(strategy = SEQUENCE, generator = "dog_generator")
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
        Assert.hasLength(name, "Name must not be empty");
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.breedDtos = breedDtos;
    }

    public DogDto(String name, String description, Gender gender) {
        this(name, description, gender, new HashSet<BreedDto>());
    }

    public DogDto(Dog dog) {
        this(dog.getId(), dog.getName(), dog.getDescription(), dog.getGender(),
            dog.getBreed().stream().map(breed -> new BreedDto(breed)).collect(Collectors.toSet()));
    }
}
