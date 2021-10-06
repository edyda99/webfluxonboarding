package com.example.webfluxonboarding.integration.services;


import com.example.webfluxonboarding.integration.entities.Album;
import com.example.webfluxonboarding.integration.entities.User;
import reactor.core.publisher.Mono;

public interface AlbumsCall {
    Mono<Album> getAlbum(String id);
    Mono<User> getAlbumsByUserId(User user);
}
