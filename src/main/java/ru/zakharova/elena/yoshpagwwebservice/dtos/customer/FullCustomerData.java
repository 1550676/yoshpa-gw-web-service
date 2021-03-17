package ru.zakharova.elena.yoshpagwwebservice.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullCustomerData {

    private Long id;
    private String alias;
    private String firstName;
    private boolean visibleFirstName;
    private String lastName;
    private boolean visibleLastName;
    private List<Email> emails;
    private List<Address> addresses;
    private List<Phone> phones;

}
