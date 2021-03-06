package com.sawert.swagger.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class RepositoryConfig {
    // empty config to limit autoconfigure for unit tests
}
