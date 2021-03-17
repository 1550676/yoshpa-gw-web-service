package ru.zakharova.elena.yoshpagwwebservice.exception;

public class UnsupportedAuthorizationType extends RuntimeException{
    public UnsupportedAuthorizationType(String message) {
        super(message);
    }
}
