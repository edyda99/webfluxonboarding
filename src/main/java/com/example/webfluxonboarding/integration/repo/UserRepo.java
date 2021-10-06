package com.example.webfluxonboarding.integration.repo;


import com.example.webfluxonboarding.integration.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User,Long> {
    Optional<User> findById(String aLong);
}
