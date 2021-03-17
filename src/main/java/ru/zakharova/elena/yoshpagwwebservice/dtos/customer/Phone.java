package ru.zakharova.elena.yoshpagwwebservice.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    private Long id;
    private String phone;
    private Long customerId;
    private boolean visible;
}
