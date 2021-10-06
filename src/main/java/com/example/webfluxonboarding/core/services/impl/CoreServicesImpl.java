package com.example.webfluxonboarding.core.services.impl;

import com.example.webfluxonboarding.core.dto.request.AlbumRequestDto;
import com.example.webfluxonboarding.core.dto.request.PhotoRequestDto;
import com.example.webfluxonboarding.core.dto.response.AlbumResponseDto;
import com.example.webfluxonboarding.core.dto.response.PhotoResponseDto;
import com.example.webfluxonboarding.core.dto.response.UserResponseDto;
import com.example.webfluxonboarding.core.services.CoreServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class CoreServicesImpl implements CoreServices {


    @Override
    public Flux<PhotoResponseDto> getAllPhotos() {
        return null;
    }

    @Override
    public Flux<AlbumResponseDto> getAllAlbums() {
        return null;
    }

    @Override
    public Flux<UserResponseDto> getAllUsers() {
        return null;
    }

    @Override
    public Mono<UserResponseDto> getUserDetails(Long id) {
        return null;
    }

    @Override
    public void createPhoto(PhotoRequestDto dto) {

    }

    @Override
    public void createAlbum(AlbumRequestDto dto) {

    }

    @Override
    public void updatePhoto(PhotoRequestDto dto) {

    }

    @Override
    public void updateAlbum(AlbumRequestDto dto) {

    }

    @Override
    public void deletePhoto(Long dto) {

    }

    @Override
    public void deleteAlbum(Long dto) {

    }
}
