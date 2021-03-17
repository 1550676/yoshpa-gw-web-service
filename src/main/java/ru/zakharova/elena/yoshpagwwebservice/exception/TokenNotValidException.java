package ru.zakharova.elena.yoshpagwwebservice.exception;

public class TokenNotValidException extends RuntimeException {
    public TokenNotValidException(String message) {
        super(message);
    }
}
