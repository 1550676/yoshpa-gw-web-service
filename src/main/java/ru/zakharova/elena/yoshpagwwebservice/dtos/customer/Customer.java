package ru.zakharova.elena.yoshpagwwebservice.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long id;
    private String alias;
    private String firstName;
    private boolean visibleFirstName;
    private String lastName;
    private boolean visibleLastName;
}
