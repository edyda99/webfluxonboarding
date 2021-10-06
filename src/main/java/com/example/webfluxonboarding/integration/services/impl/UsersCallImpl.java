package com.example.webfluxonboarding.integration.services.impl;

import com.example.webfluxonboarding.core.dto.response.AddressResponseDto;
import com.example.webfluxonboarding.core.dto.response.CompanyResponseDto;
import com.example.webfluxonboarding.core.dto.response.GeoResponseDto;
import com.example.webfluxonboarding.core.dto.response.UserResponseDto;
import com.example.webfluxonboarding.integration.entities.Album;
import com.example.webfluxonboarding.integration.entities.User;
import com.example.webfluxonboarding.integration.entities.embedded.Address;
import com.example.webfluxonboarding.integration.entities.embedded.Company;
import com.example.webfluxonboarding.integration.entities.embedded.Geo;
import com.example.webfluxonboarding.integration.model.response.AlbumResponse;
import com.example.webfluxonboarding.integration.model.response.UserResponse;
import com.example.webfluxonboarding.integration.repo.UserRepo;
import com.example.webfluxonboarding.integration.services.AlbumsCall;
import com.example.webfluxonboarding.integration.services.UsersCall;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsersCallImpl implements UsersCall {
    private final WebClient webClient;
    private final UserRepo userRepo;
    private final AlbumsCall albumsCall;

    @Override
    public Mono<User> getUser(String id) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("id",id)
                        .build())
                .retrieve()
                .bodyToFlux(UserResponse.class)
                .single()
                .map(p->new User()
                        .setId(p.getId())
                        .setCompany(new Company()
                                .setBs(p.getCompany().getBs())
                                .setName(p.getCompany().getName())
                                .setCatchPhrase(p.getCompany().getCatchPhrase()))
                        .setAddress(new Address()
                                .setCity(p.getAddress().getCity())
                                .setGeo(new Geo()
                                        .setLat(p.getAddress().getGeo().getLat())
                                        .setLng(p.getAddress().getGeo().getLng()))
                                .setStreet(p.getAddress().getStreet())
                                .setSuite(p.getAddress().getSuite())
                                .setZipcode(p.getAddress().getZipcode()))
                        .setName(p.getName())
                        .setEmail(p.getEmail())
                        .setUsername(p.getUsername())
                        .setPhone(p.getPhone())
                        .setWebsite(p.getWebsite()))
                .flatMap(this::fillDb)
                .flatMap(this::saveDb)
                .log("Album imported successfully");
    }

    private Mono<User> saveDb(User user) {
    userRepo.save(user);
    return Mono.just(user);
    }

    private Mono<User> fillDb(User user) {
        return albumsCall.getAlbumsByUserId(user);
    }
}