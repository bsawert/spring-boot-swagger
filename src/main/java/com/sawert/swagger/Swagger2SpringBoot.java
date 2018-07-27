package com.sawert.swagger;

import com.sawert.swagger.entity.BreedDto;
import com.sawert.swagger.entity.DogDto;
import com.sawert.swagger.model.AKCGroup;
import com.sawert.swagger.model.Gender;
import com.sawert.swagger.repository.BreedRepository;
import com.sawert.swagger.repository.DogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.sawert.swagger", "com.sawert.swagger.api" })
public class Swagger2SpringBoot implements CommandLineRunner {

	@Bean
	CommandLineRunner initData(BreedRepository breedRepository, DogRepository dogRepository){
		return args -> {
			BreedDto breedDto = breedRepository.save(
                new BreedDto("Pug", "Pug", AKCGroup.TOY));
			DogDto dogDto = dogRepository.save(
                new DogDto("Gidget", "Gidget", Gender.FEMALE, breedDto));
		};
	}

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }
    }
}
