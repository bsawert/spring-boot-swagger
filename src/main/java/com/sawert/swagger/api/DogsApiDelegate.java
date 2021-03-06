package com.sawert.swagger.api;

import com.sawert.swagger.model.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link DogsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface DogsApiDelegate {

    Logger log = LoggerFactory.getLogger(DogsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    /**
     * @see DogsApi#addDog
     */
    default ResponseEntity<Dog> addDog(Dog dog) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"gender\" : { },  \"name\" : \"Fluffy\",  \"description\" : \"Pound Puppy\",  \"id\" : 1,  \"breed\" : [ {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  }, {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  } ]}", Dog.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DogsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see DogsApi#fetchDogs
     */
    default ResponseEntity<List<Dog>> fetchDogs() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"gender\" : { },  \"name\" : \"Fluffy\",  \"description\" : \"Pound Puppy\",  \"id\" : 1,  \"breed\" : [ {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  }, {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  } ]}, {  \"gender\" : { },  \"name\" : \"Fluffy\",  \"description\" : \"Pound Puppy\",  \"id\" : 1,  \"breed\" : [ {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  }, {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  } ]} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DogsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see DogsApi#getDog
     */
    default ResponseEntity<Dog> getDog(Long id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"gender\" : { },  \"name\" : \"Fluffy\",  \"description\" : \"Pound Puppy\",  \"id\" : 1,  \"breed\" : [ {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  }, {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  } ]}", Dog.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DogsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see DogsApi#searchDog
     */
    default ResponseEntity<List<Dog>> searchDog(String name,
        String breed) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"gender\" : { },  \"name\" : \"Fluffy\",  \"description\" : \"Pound Puppy\",  \"id\" : 1,  \"breed\" : [ {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  }, {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  } ]}, {  \"gender\" : { },  \"name\" : \"Fluffy\",  \"description\" : \"Pound Puppy\",  \"id\" : 1,  \"breed\" : [ {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  }, {    \"name\" : \"Blue Tick Hound\",    \"description\" : \"Blue Tick Hound\",    \"id\" : 1,    \"akcgroup\" : { }  } ]} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
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
