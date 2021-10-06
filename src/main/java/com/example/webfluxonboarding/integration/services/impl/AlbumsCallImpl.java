package com.example.webfluxonboarding.integration.services.impl;


import com.eurisko.onboardingexercise.project.module.core.exceptions.DbException;
import com.example.webfluxonboarding.integration.entities.Album;
import com.example.webfluxonboarding.integration.entities.User;
import com.example.webfluxonboarding.integration.model.response.AlbumResponse;
import com.example.webfluxonboarding.integration.repo.AlbumRepo;
import com.example.webfluxonboarding.integration.repo.PhotoRepo;
import com.example.webfluxonboarding.integration.repo.UserRepo;
import com.example.webfluxonboarding.integration.services.AlbumsCall;
import com.example.webfluxonboarding.integration.services.PhotosCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AlbumsCallImpl implements AlbumsCall {
    private final WebClient webClient;
    private final AlbumRepo albumRepo;
    private final UserRepo userRepo;
    private final PhotosCall photosCall;

    @Override
    public Mono<Album> getAlbum(String id) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/albums")
                .queryParam("id",id)
                .build())
                .retrieve()
                .bodyToFlux(AlbumResponse.class)
                .single()
                .map(p->new Album()
                        .setId(p.getId())
                        .setDate(String.valueOf(new Date(System.currentTimeMillis())))
                        .setTitle(p.getTitle())
                        .setUserId(p.getUserId()))
                .doOnNext(albumRepo::save)
                .doOnNext(this::linkAlbumTOUser)
                .log("Album imported successfully");
    }

    @Override
    public Mono<User> getAlbumsByUserId(User user) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/albums")
                        .queryParam("userId",user.getId())
                        .build())
                .retrieve()
                .bodyToFlux(AlbumResponse.class)
                .map(p->new Album()
                        .setId(p.getId())
                        .setDate(String.valueOf(new Date(System.currentTimeMillis())))
                        .setTitle(p.getTitle())
                        .setUserId(p.getUserId()))
                .flatMap(p->linkAlbumTOUser(p,user))
                .reduce(this::linkAlbumTOUser)
                .log("Album imported successfully");
    }

    private User linkAlbumTOUser(User user, User user1) {
        return user;
    }

    private void linkAlbumTOUser(Album album) {
        Optional<User> user = userRepo.findById(album.getUserId());
        if(user.isPresent()) {
            user.get().getAlbums().add(album);
            userRepo.save(user.get());
        }
        else {
            log.error("Album with id {} is not linked to any user",album.getId());
            throw new DbException("10452");
        }
    }

    private Mono<User> linkAlbumTOUser(Album album,User user) {
        return photosCall.getPhotosByAlbumsId(album,user);
    }
}
