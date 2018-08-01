package com.sawert.swagger.service;

import com.sawert.swagger.model.Dog;

import java.util.List;

public interface DogsService {
    public Dog addDog(Dog dog);
    public List<Dog> getDogs();
    public Dog getDog(Long id);
    public Dog getDogByName(String name);
}
