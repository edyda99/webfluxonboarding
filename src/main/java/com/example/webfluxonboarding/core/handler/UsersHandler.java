package com.example.webfluxonboarding.core.handler;

import com.example.webfluxonboarding.core.dto.request.AlbumRequestDto;
import com.example.webfluxonboarding.core.dto.request.PhotoRequestDto;
import com.example.webfluxonboarding.core.dto.response.*;
import com.example.webfluxonboarding.core.exceptions.DbException;
import com.example.webfluxonboarding.integration.entities.Album;
import com.example.webfluxonboarding.integration.entities.Photo;
import com.example.webfluxonboarding.integration.entities.User;
import com.example.webfluxonboarding.integration.repo.AlbumRepo;
import com.example.webfluxonboarding.integration.repo.PhotoRepo;
import com.example.webfluxonboarding.integration.repo.UserRepo;
import com.example.webfluxonboarding.integration.services.AlbumsCall;
import com.example.webfluxonboarding.integration.services.PhotosCall;
import com.example.webfluxonboarding.integration.services.UsersCall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;

@Component
@RequiredArgsConstructor
public class UsersHandler {
    private final PhotoRepo photoRepo;
    private final PhotosCall photosCall;
    private final AlbumRepo albumRepo;
    private final AlbumsCall albumsCall;
    private final UsersCall usersCall;
    private final UserRepo userRepo;


    public Mono<ServerResponse> getPhoto(ServerRequest serverRequest) {
        Optional<String> id = serverRequest.queryParam("id");
        if (id.isEmpty()) return badRequest().build();
        Mono<Photo> response;
        Optional<Photo> photo = photoRepo.findById(id.get());
        response = photo.map(Mono::just).orElseGet(() -> photosCall.getPhotos(id.get()));

        response.map(this::getPhotoResponseDto
        );

        return response.flatMap(p->ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(fromValue(p)))
                .onErrorResume(e->Mono.just("Error" + e)
                        .flatMap(p-> ServerResponse.ok()
                                .contentType(APPLICATION_JSON)
                                .bodyValue(p)));
    }
    public Mono<ServerResponse> getAlbum(ServerRequest serverRequest) {
        Optional<String> id = serverRequest.queryParam("id");
        if (id.isEmpty()) return badRequest().build();

        Mono<Album> response;
        Optional<Album> album = albumRepo.findById(id.get());
        response = album.map(Mono::just).orElseGet(() -> albumsCall.getAlbum(id.get()));

        response.map(this::getAlbumResponseDto);

        return response.flatMap(p->ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(fromValue(p)))
                .onErrorResume(e->Mono.just("Error" + e)
                        .flatMap(p-> ServerResponse.ok()
                                .contentType(APPLICATION_JSON)
                                .bodyValue(p)));
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        Optional<String> id = serverRequest.queryParam("id");
        if (id.isEmpty()) return badRequest().build();
        Mono<User> response;
        Optional<User> user = userRepo.findById(id.get());
        response = user.map(Mono::just).orElseGet(()-> usersCall.getUser(id.get()));

        response.map(this::getUserResponseDto);

        return response
                .flatMap(p->ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(fromValue(p)))
                .onErrorResume(e->Mono.just("Error" + e)
                .flatMap(p-> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .bodyValue(p)));
    }

    public Mono<ServerResponse> createOrUpdateAlbum(ServerRequest serverRequest){
        try {
            Mono<Album> response = serverRequest.body(toMono(AlbumRequestDto.class))
                    .flatMap(this::dtoToEntity)
                    .doOnNext(albumRepo::save);
            return response.flatMap(p->ServerResponse.ok()
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("Success")))
                    .onErrorResume(e->Mono.just("Error" + e)
                    .flatMap(p-> ServerResponse.ok()
                            .contentType(APPLICATION_JSON)
                            .bodyValue(p)));
        }catch (Exception ex){
            throw new DbException(ex.getMessage());
        }
    }

    public Mono<ServerResponse> createOrUpdatePhoto(ServerRequest serverRequest){
        try {
            Mono<Photo> response = serverRequest.body(toMono(PhotoRequestDto.class))
                    .flatMap(this::dtoToEntity)
                    .doOnNext(photoRepo::save);
            return response.flatMap(p->ServerResponse.ok()
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("Success")))
                    .onErrorResume(e->Mono.just("Error" + e)
                            .flatMap(p-> ServerResponse.ok()
                                    .contentType(APPLICATION_JSON)
                                    .bodyValue(p)));
        }catch (Exception ex){
            throw new DbException(ex.getMessage());
        }
    }
    public Mono<ServerResponse> deleteAlbum(ServerRequest serverRequest){
        try {
            Optional<String> id = serverRequest.queryParam("id");
            id.ifPresentOrElse(albumRepo::deleteById, ServerResponse::badRequest);
            return ServerResponse.ok()
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("Success"))
                    .onErrorResume(e->Mono.just("Error" + e)
                            .flatMap(p-> ServerResponse.ok()
                                    .contentType(APPLICATION_JSON)
                                    .bodyValue(p)));
        }catch (Exception ex){
            throw new DbException(ex.getMessage());
        }
    }

    public Mono<ServerResponse> deletePhoto(ServerRequest serverRequest){
        try {
            Optional<String> id = serverRequest.queryParam("id");
            id.ifPresentOrElse(photoRepo::deleteById,ServerResponse::badRequest);
            return ServerResponse.ok()
                    .contentType(APPLICATION_JSON)
                    .body(fromValue("Success"))
                    .onErrorResume(e->Mono.just("Error" + e)
                            .flatMap(p-> ServerResponse.ok()
                                    .contentType(APPLICATION_JSON)
                                    .bodyValue(p)));
        }catch (Exception ex){
            throw new DbException(ex.getMessage());
        }
    }

    private Mono<Album> dtoToEntity(AlbumRequestDto p) {
    return Mono.just(new Album().setUserId(p.getUserId()).setId(p.getId()).setTitle(p.getTitle()).setDate(String.valueOf(new Date(System.currentTimeMillis()))));
    }
    private Mono<Photo> dtoToEntity(PhotoRequestDto p) {
        return Mono.just(new Photo().setAlbumId(p.getAlbumId()).setId(p.getId()).setTitle(p.getTitle()).setDate(String.valueOf(new Date(System.currentTimeMillis()))).setUrl(p.getUrl()).setThumbnailUrl(p.getThumbnailUrl()));
    }

    private UserResponseDto getUserResponseDto(User p) {
        UserResponseDto responseDto = new UserResponseDto()
                .setId(p.getId())
                .setCompany(new CompanyResponseDto()
                        .setBs(p.getCompany().getBs())
                        .setName(p.getCompany().getName())
                        .setCatchPhrase(p.getCompany().getCatchPhrase()))
                .setAddress(new AddressResponseDto()
                        .setCity(p.getAddress().getCity())
                        .setGeo(new GeoResponseDto()
                                .setLat(p.getAddress().getGeo().getLat())
                                .setLng(p.getAddress().getGeo().getLng()))
                        .setStreet(p.getAddress().getStreet())
                        .setSuite(p.getAddress().getSuite())
                        .setZipcode(p.getAddress().getZipcode()))
                .setName(p.getName())
                .setEmail(p.getEmail())
                .setUsername(p.getUsername())
                .setPhone(p.getPhone())
                .setWebsite(p.getWebsite());
        p.getAlbums().forEach(album -> responseDto.addAlbum(getAlbumResponseDto(album)));
        return responseDto;
    }

    private AlbumResponseDto getAlbumResponseDto(Album p) {
        AlbumResponseDto responseDto = new AlbumResponseDto()
                .setDate(p.getDate())
                .setId(p.getId())
                .setTitle(p.getTitle());
        p.getPhotos().forEach(photo->responseDto.addPhoto(getPhotoResponseDto(photo)));
        return responseDto;
    }

    private PhotoResponseDto getPhotoResponseDto(Photo p) {
        return new PhotoResponseDto()
                .setId(p.getId())
                .setDate(p.getDate())
                .setUrl(p.getUrl())
                .setTitle(p.getTitle())
                .setThumbnailUrl(p.getThumbnailUrl());
    }
}
