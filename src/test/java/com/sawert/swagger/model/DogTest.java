package com.sawert.swagger.model;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

@RunWith(SpringRunner.class)
public class DogTest {

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testDogToJson() throws JsonProcessingException  {
        Dog dog = new Dog()
            .id(42L)
            .name("Fluffy")
            .description("Fluffy dog")
            .gender(Gender.FEMALE)
            .breed(ImmutableList.of(new Breed()
                .id(13L)
                .name("Mixed")
                .description("Mutt")
                .akcgroup(AKCGroup.MISCELLANEOUS)));

        String json = objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(dog);

        System.out.println(json);
    }

    @Test
    public void testJsonToDog() throws IOException, JsonProcessingException {
        String json = "{\n" +
                "  \"id\" : 42,\n" +
                "  \"name\" : \"Fluffy\",\n" +
                "  \"description\" : \"Fluffy dog\",\n" +
                "  \"gender\" : \"Female\",\n" +
                "  \"breed\" : [ {\n" +
                "    \"id\" : 13,\n" +
                "    \"name\" : \"Mixed\",\n" +
                "    \"description\" : \"Mutt\",\n" +
                "    \"akcgroup\" : \"Miscellaneous\"\n" +
                "  } ]\n" +
                "}";

        Dog dog = objectMapper.readValue(json, Dog.class);

        System.out.println(dog);
    }
}
