package com.sawert.swagger.repository;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BreedDto {

    @Id
    @SequenceGenerator(name = "breed_generator", sequenceName = "breed_sequence", initialValue = 100)
    @GeneratedValue(generator = "breed_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private AKCGroup akcgroup;

    public BreedDto(String name, String description, AKCGroup akcGroup) {
        Assert.hasLength(name, "Name must not be empty");
        this.name = name;
        this.description = description;
        this.akcgroup = akcGroup;
    }

    public BreedDto(Breed breed) {
        this(breed.getId(), breed.getName(), breed.getDescription(), breed.getAkcgroup());
    }
}
