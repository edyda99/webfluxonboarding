package com.example.webfluxonboarding.integration.services;

import com.example.webfluxonboarding.integration.entities.Album;
import com.example.webfluxonboarding.integration.entities.Photo;
import com.example.webfluxonboarding.integration.entities.User;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface PhotosCall {
    Mono<Photo> getPhotos(String id);

    Mono<User> getPhotosByAlbumsId(Album album, User user);
}
