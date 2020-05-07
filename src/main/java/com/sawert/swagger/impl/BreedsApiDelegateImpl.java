package com.sawert.swagger.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sawert.swagger.api.BreedsApiDelegate;
import com.sawert.swagger.repository.BreedDto;
import com.sawert.swagger.repository.BreedMapper;
import com.sawert.swagger.model.Breed;
import com.sawert.swagger.repository.BreedRepository;
import com.sawert.swagger.service.BreedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BreedsApiDelegateImpl implements BreedsApiDelegate {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private BreedRepository breedRepository;
    @Autowired
    private BreedsService breedsService;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.of(this.objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.of(this.httpServletRequest);
    }

    @Override
    public ResponseEntity<Breed> addBreed(Breed breed) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    BreedDto dto = breedRepository.save(new BreedDto(breed));
                    return new ResponseEntity<Breed>(BreedMapper.toBreed(dto), HttpStatus.CREATED);
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
    public ResponseEntity<List<Breed>> fetchBreeds() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    List<Breed> breeds = breedsService.getBreeds();
                    return new ResponseEntity<>(breeds, HttpStatus.OK);
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
    public ResponseEntity<Breed> getBreed(Long id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    Optional<BreedDto> dto = breedRepository.findById(id);
                    return new ResponseEntity<Breed>(BreedMapper.toBreed(dto.orElse(null)), HttpStatus.OK);
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
    public ResponseEntity<List<Breed>> searchBreed(String name, String akcgroup) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    List<BreedDto> dtos = breedRepository.findByNameIgnoreCase(name);
                    return new ResponseEntity<>(
                        dtos.stream().map(BreedMapper::toBreed).collect(Collectors.toList()),
                        HttpStatus.OK);
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
}
