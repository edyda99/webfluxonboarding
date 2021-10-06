package com.example.webfluxonboarding.integration.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Data
public class ExerciseProperties {
    @Value("${api.url.base-url}")
    private String baseUrl;
}
