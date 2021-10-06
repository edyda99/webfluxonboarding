package com.example.webfluxonboarding;

import com.example.webfluxonboarding.integration.repo.AlbumRepo;
import com.example.webfluxonboarding.integration.repo.PhotoRepo;
import com.example.webfluxonboarding.integration.repo.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {AlbumRepo.class, PhotoRepo.class, UserRepo.class})
public class WebfluxonboardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxonboardingApplication.class, args);
    }

}
