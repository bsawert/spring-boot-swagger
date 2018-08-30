package com.sawert.swagger.service;

import com.sawert.swagger.model.Breed;
import com.sawert.swagger.model.Dog;

import java.util.List;
import java.util.Set;

public interface DogsService {

    /**
     * Add a dog to the repository
     *
     * @param dog
     * @return saved dog
     */
    public Dog addDog(Dog dog);

    /**
     * List all dogs
     *
     * @return list of dogs
     */
    public List<Dog> getDogs();

    /**
     * Get dog by id
     *
     * @param id
     * @return dog or null if not found
     */
    public Dog getDog(Long id);

    /**
     * Get dog by name
     *
     * @param name
     * @return first breed matched by name or null if not found
     */
    public List<Dog> getDogByName(String name);

    /**
     *
     * Get dogs by breed
     *
     * @param breed
     * @return dogs belonging to breed, empty if not found
     */
    public List<Dog> getDogsByBreed(Breed breed);

    /**
     * Get dogs by breeds
     *
     * @param breedSet
     * @return dogs belonging to any breed in set, empty if not found
     */
    public List<Dog> getDogsByBreeds(Set<Breed> breedSet);


}
