package com.example.webfluxonboarding.core.exceptions;

public class DbException extends RuntimeException{
    public DbException(){
        super();
    }
    public DbException(String error){
        super(error);
    }
}
