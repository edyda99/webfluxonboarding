package com.eurisko.onboardingexercise.project.module.core.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@RequiredArgsConstructor
public class DbEx {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final Date time;

}
