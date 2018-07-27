package com.sawert.swagger.entity;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BreedDto {

    @Id
    @SequenceGenerator(name = "breed_generator", sequenceName = "breed_sequence", initialValue = 100)
    @GeneratedValue(generator = "breed_generator")
    private Long id;

    @Column(nullable = false)
    @NaturalId
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private AKCGroup akcgroup;

    public BreedDto(Breed breed) {
        this(breed.getId(), breed.getName(), breed.getDescription(), breed.getAkcgroup());
    }

    public Breed toBreed() {
        return new Breed()
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .akcgroup(this.akcgroup);
    }
}
