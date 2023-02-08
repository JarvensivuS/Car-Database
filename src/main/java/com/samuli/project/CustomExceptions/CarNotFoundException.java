package com.samuli.project.CustomExceptions;

public class CarNotFoundException extends Exception{
    public CarNotFoundException(String message){
        super(message);
    }
}
