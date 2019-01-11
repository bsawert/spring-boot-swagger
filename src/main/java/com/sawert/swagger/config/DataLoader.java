package com.sawert.swagger.config;

import com.sawert.swagger.repository.BreedDto;
import com.sawert.swagger.repository.DogDto;
import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Gender;
import com.sawert.swagger.repository.BreedRepository;
import com.sawert.swagger.repository.DogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataLoader {
    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner initData(BreedRepository breedRepository, DogRepository dogRepository) {
        return args -> {
            BreedDto breedDto = breedRepository.save(
                new BreedDto("Pug", "Pug", AKCGroup.TOY));
            DogDto dogDto = dogRepository.save(
                new DogDto("Gidget", "Gidget", Gender.FEMALE,
                    Collections.<BreedDto>singleton(breedDto)));

            Set<BreedDto> breedDtoSet =  new HashSet<BreedDto>();
            breedDtoSet.add(breedRepository.save(
                new BreedDto("Chihuahua", "Chihuahua", AKCGroup.TOY)));
            breedDtoSet.add(breedRepository.save(
                new BreedDto("Dachshund", "Weiner Dog", AKCGroup.TOY)));
            dogDto = dogRepository.save(
                new DogDto("Lily", "Lily", Gender.FEMALE, breedDtoSet));
        };
    }

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }
}
