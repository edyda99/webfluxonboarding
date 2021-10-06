package com.example.webfluxonboarding;

import com.example.webfluxonboarding.core.exceptions.DbException;
import com.example.webfluxonboarding.core.handler.UsersHandler;
import com.example.webfluxonboarding.integration.util.ExerciseProperties;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebFilter;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ApplicationConfig {

    @Bean
    WebClient webClient(ExerciseProperties exerciseProperties) {
        return WebClient.create(exerciseProperties.getBaseUrl());
    }

//    @Bean
//    DefaultErrorAttributes defaultErrorAttributes() {
//        return new DefaultErrorAttributes() {
//            @Override
//            public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
//                    Map<String, Object> map = super.getErrorAttributes(
//                            request, options);
//                    map.put("status", HttpStatus.BAD_REQUEST);
//                    return map;
//                }
//        };
//    }

    @Bean
    public RouterFunction<ServerResponse> route(UsersHandler handlerFunction) {

        return RouterFunctions
                .route(GET("/api/v1/get-user").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getUser)
                .andRoute(GET("/api/v1/get-album").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getAlbum)
                .andRoute(GET("/api/v1/get-photo").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getPhoto)
                .andRoute(GET("/api/v1/delete-album").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::deleteAlbum)
                .andRoute(GET("/api/v1/delete-photo").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::deletePhoto)
                .andRoute(POST("/api/v1/create-album").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::createOrUpdateAlbum)
                .andRoute(POST("/api/v1/create-photo").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::createOrUpdatePhoto);
    }

}
