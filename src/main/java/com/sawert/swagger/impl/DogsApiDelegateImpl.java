package com.sawert.swagger.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sawert.swagger.api.DogsApiDelegate;
import com.sawert.swagger.entity.DogDto;
import com.sawert.swagger.entity.DogMapper;
import com.sawert.swagger.model.Dog;
import com.sawert.swagger.repository.DogRepository;
import com.sawert.swagger.service.DogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DogsApiDelegateImpl implements DogsApiDelegate {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private DogsService dogsService;

    @Autowired
    public DogsApiDelegateImpl(ObjectMapper objectMapper, HttpServletRequest httpServletRequest) {
        this.objectMapper = objectMapper;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.of(this.objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.of(this.httpServletRequest);
    }

    @Override
    public ResponseEntity<Dog> addDog(Dog dog) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    DogDto dto = dogRepository.save(new DogDto(dog));
                    return new ResponseEntity<Dog>(DogMapper.toDog(dto), HttpStatus.CREATED);
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DogsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<List<Dog>> fetchDogs() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    List<Dog> dogs = dogsService.getDogs();
                    return new ResponseEntity<>(dogs, HttpStatus.OK);
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default BreedsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Dog> getDog(Long id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    DogDto dto = dogRepository.findOne(id);
                    return new ResponseEntity<Dog>(DogMapper.toDog(dto), HttpStatus.OK);
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DogsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
