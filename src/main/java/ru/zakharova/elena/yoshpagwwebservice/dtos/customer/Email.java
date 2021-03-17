package ru.zakharova.elena.yoshpagwwebservice.dtos.customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private Long id;
    private String email;
    private Long customerId;
    private boolean visible;
}
