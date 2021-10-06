package com.example.webfluxonboarding.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

public class DbExceptionHandler {
    @ExceptionHandler(value = {DbException.class})
    public ResponseEntity<Object> handleDbException(DbException ex){
        DbEx dbEx = new DbEx(ex.getMessage(), ex, HttpStatus.BAD_REQUEST, new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(dbEx,HttpStatus.BAD_REQUEST);
    }
}
