package com.example.webfluxonboarding.integration.repo;


import com.example.webfluxonboarding.integration.entities.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlbumRepo extends MongoRepository<Album,Long> {
    Optional<Album> findById(String st);
    void deleteById(String st);
}
