package ru.zakharova.elena.yoshpagwwebservice.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long id;
    private String address;
    private Long customerId;
    private boolean visible;
}
