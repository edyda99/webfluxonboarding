package com.example.webfluxonboarding.integration.repo;


import com.example.webfluxonboarding.integration.entities.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PhotoRepo extends MongoRepository<Photo,Long> {
    Optional<Photo> findById(String aLong);
    void deleteById(String id);
}
