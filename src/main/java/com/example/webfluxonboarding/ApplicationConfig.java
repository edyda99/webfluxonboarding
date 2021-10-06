package com.example.webfluxonboarding;

import com.example.webfluxonboarding.core.exceptions.DbException;
import com.example.webfluxonboarding.core.handler.UsersHandler;
import com.example.webfluxonboarding.integration.util.ExerciseProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.web.cors.CorsConfiguration.ALL;
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
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList(ALL));
        corsConfig.setMaxAge(8000L);
        corsConfig.addAllowedMethod(ALL);
        corsConfig.addAllowedHeader(ALL);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public RouterFunction<ServerResponse> route(UsersHandler handlerFunction) {

        return RouterFunctions
                .route(GET("/api/v1/get-user").and(accept(MediaType.ALL)), handlerFunction::getUser)
                .andRoute(GET("/api/v1/get-album").and(accept(MediaType.ALL)), handlerFunction::getAlbum)
                .andRoute(GET("/api/v1/get-photo").and(accept(MediaType.ALL)), handlerFunction::getPhoto)
                .andRoute(GET("/api/v1/delete-album").and(accept(MediaType.ALL)), handlerFunction::deleteAlbum)
                .andRoute(GET("/api/v1/delete-photo").and(accept(MediaType.ALL)), handlerFunction::deletePhoto)
                .andRoute(POST("/api/v1/create-album").and(accept(MediaType.ALL)), handlerFunction::createOrUpdateAlbum)
                .andRoute(POST("/api/v1/create-photo").and(accept(MediaType.ALL)), handlerFunction::createOrUpdatePhoto);
    }

}
