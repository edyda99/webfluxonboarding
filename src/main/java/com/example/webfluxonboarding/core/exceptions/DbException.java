package com.eurisko.onboardingexercise.project.module.core.exceptions;

public class DbException extends RuntimeException{
    public DbException(){
        super();
    }
    public DbException(String error){
        super(error);
    }
}
