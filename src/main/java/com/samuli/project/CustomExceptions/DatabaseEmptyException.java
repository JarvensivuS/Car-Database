package com.samuli.project.CustomExceptions;

public class DatabaseEmptyException extends Exception {
    public DatabaseEmptyException(String message){
        super(message);
    }
}
