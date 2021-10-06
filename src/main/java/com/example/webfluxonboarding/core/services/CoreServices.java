package com.example.webfluxonboarding.core.services;

import com.example.webfluxonboarding.core.dto.request.AlbumRequestDto;
import com.example.webfluxonboarding.core.dto.request.PhotoRequestDto;
import com.example.webfluxonboarding.core.dto.response.AlbumResponseDto;
import com.example.webfluxonboarding.core.dto.response.PhotoResponseDto;
import com.example.webfluxonboarding.core.dto.response.UserResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoreServices {
    Flux<PhotoResponseDto> getAllPhotos();
    Flux<AlbumResponseDto> getAllAlbums();
    Flux<UserResponseDto> getAllUsers();

    Mono<UserResponseDto> getUserDetails(Long id);

    void createPhoto(PhotoRequestDto dto);
    void createAlbum(AlbumRequestDto dto);

    void updatePhoto(PhotoRequestDto dto);
    void updateAlbum(AlbumRequestDto dto);

    void deletePhoto(Long dto);
    void deleteAlbum(Long dto);

}
