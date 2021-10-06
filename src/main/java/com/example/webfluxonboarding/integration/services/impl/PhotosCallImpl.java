package com.example.webfluxonboarding.integration.services.impl;

import com.eurisko.onboardingexercise.project.module.core.exceptions.DbException;
import com.example.webfluxonboarding.integration.entities.Album;
import com.example.webfluxonboarding.integration.entities.Photo;
import com.example.webfluxonboarding.integration.entities.User;
import com.example.webfluxonboarding.integration.model.response.AlbumResponse;
import com.example.webfluxonboarding.integration.model.response.PhotoResponse;
import com.example.webfluxonboarding.integration.repo.AlbumRepo;
import com.example.webfluxonboarding.integration.repo.PhotoRepo;
import com.example.webfluxonboarding.integration.services.PhotosCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PhotosCallImpl implements PhotosCall {
    private final WebClient webClient;
    private final PhotoRepo photoRepo;
    private final AlbumRepo albumRepo;

    @Override
    public Mono<Photo> getPhotos(String id) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/photos")
                        .queryParam("id", id)
                        .build())
                .retrieve()
                .bodyToFlux(PhotoResponse.class)
                .single()
                .map(p -> new Photo()
                        .setId(p.getId())
                        .setDate(String.valueOf(new Date(System.currentTimeMillis())))
                        .setTitle(p.getTitle())
                        .setAlbumId(p.getAlbumId())
                        .setThumbnailUrl(p.getThumbnailUrl())
                        .setUrl(p.getUrl()))
                .doOnNext(photoRepo::save)
                .doOnNext(this::linkPhotoToAlbum)
                .log("Photo imported successfully");
    }

    @Override
    public Mono<User> getPhotosByAlbumsId(Album album, User user) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/photos")
                        .queryParam("albumId", album.getId())
                        .build())
                .retrieve()
                .bodyToFlux(PhotoResponse.class)
                .map(p -> new Photo()
                        .setId(p.getId())
                        .setDate(String.valueOf(new Date(System.currentTimeMillis())))
                        .setTitle(p.getTitle())
                        .setAlbumId(p.getAlbumId())
                        .setThumbnailUrl(p.getThumbnailUrl())
                        .setUrl(p.getUrl()))
                .doOnNext(photoRepo::save)
                .map(p->linkPhotoToAlbum(p,album,user))
                .doOnComplete(new Thread(()->{
                    albumRepo.save(album);
                }))
                .reduce(this::linkPhotoToAlbum)
                .log("Photo imported successfully");
    }

    private User linkPhotoToAlbum(User user, User user1) {
    return user;
    }

    private User linkPhotoToAlbum(Photo photo,Album album,User user) {
             return user.addAlbum(album.addPhoto(photo));
    }

    private void linkPhotoToAlbum(Photo photo) {
        Optional<Album> album = albumRepo.findById(photo.getAlbumId());
        if(album.isPresent()) album.get().getPhotos().add(photo);
        else {
            log.error("photo with id {} is not linked to any album",photo.getId());
            throw new DbException("photo with id: " + photo.getId() + " is not linked to any album");
        }
    }
}
