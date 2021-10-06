package com.example.webfluxonboarding;

import com.example.webfluxonboarding.integration.repo.AlbumRepo;
import com.example.webfluxonboarding.integration.repo.PhotoRepo;
import com.example.webfluxonboarding.integration.repo.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {AlbumRepo.class, PhotoRepo.class, UserRepo.class})
public class WebfluxonboardingApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebfluxonboardingApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.REACTIVE);
          SpringApplication.run(WebfluxonboardingApplication.class, args);
    }
}
