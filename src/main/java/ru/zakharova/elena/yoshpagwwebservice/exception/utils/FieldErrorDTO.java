package ru.zakharova.elena.yoshpagwwebservice.exception.utils;

import lombok.Data;

@Data
public class FieldErrorDTO {
    private String field;
    private String message;

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
