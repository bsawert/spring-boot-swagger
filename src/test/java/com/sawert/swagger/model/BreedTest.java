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
public class BreedTest {

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testBreedToJson() throws JsonProcessingException  {
        Breed breed = new Breed()
            .id(42L)
            .name("Golden Retriever")
            .description("Golden Retriever")
            .akcgroup(AKCGroup.SPORTING);

        String json = objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(breed);

        System.out.println(json);
    }

    @Test
    public void testJsonToBreed() throws IOException, JsonProcessingException {
        String json = "{\n" +
                "  \"id\" : 42,\n" +
                "  \"name\" : \"Golden Retriever\",\n" +
                "  \"description\" : \"Golden Retriever\",\n" +
                "  \"akcgroup\" : \"Sporting\"\n" +
                "  } ]\n" +
                "}";

        Breed breed = objectMapper.readValue(json, Breed.class);

        System.out.println(breed);
    }
}
