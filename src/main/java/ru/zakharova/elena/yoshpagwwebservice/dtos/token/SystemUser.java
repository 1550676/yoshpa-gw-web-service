package ru.zakharova.elena.yoshpagwwebservice.dtos.token;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing data about users for his registration in the application.")
public class SystemUser {

    @ApiModelProperty(notes = "Unique email. No two users can have the same email.", example = "ivan@mail.ru", required = true, position = 1)
    private String email;

    @ApiModelProperty(notes = "User's password", example = "1000", required = true, position = 2)
    private String password;

}