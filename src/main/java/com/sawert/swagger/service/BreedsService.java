package com.sawert.swagger.service;

import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Breed;

import java.util.List;
import java.util.Set;

public interface BreedsService {
    /**
     * Add a breed to the repository
     *
     * @param breed
     * @return saved breed
     */
    public Breed addBreed(Breed breed);

    /**
     * List all breeds
     *
     * @return list of breeds
     */
    public List<Breed> getBreeds();

    /**
     * Get breed by id
     *
     * @param id
     * @return breed or null if not found
     */
    public Breed getBreed(Long id);

    /**
     * Get breed by name
     *
     * @param name
     * @return first breed matched by name or null if not found
     */
    public Breed getBreedByName(String name);

    /**
     * Get breeds by AKC group
     *
     * @param akcGroup
     * @return breeds belonging to group, empty if not found
     */
    public List<Breed> getBreedsByAkcGroup(AKCGroup akcGroup);

    /**
     * Get breeds by AKC groups
     * @param akcGroupSet
     * @return breeds belonging to any group in set, empty if not found
     */
    public List<Breed> getBreedsByAkcGroups(Set<AKCGroup> akcGroupSet);
}
