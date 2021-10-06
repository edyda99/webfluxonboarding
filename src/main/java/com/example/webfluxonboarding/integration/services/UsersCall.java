package com.example.webfluxonboarding.integration.services;


import com.example.webfluxonboarding.integration.entities.User;
import reactor.core.publisher.Mono;

public interface UsersCall {
    Mono<User> getUser(String id);
}
