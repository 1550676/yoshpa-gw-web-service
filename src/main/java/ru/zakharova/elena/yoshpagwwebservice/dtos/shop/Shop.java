package ru.zakharova.elena.yoshpagwwebservice.dtos.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing data about shop for displaying in personal account.")
public class Shop {

    @ApiModelProperty(notes = "Unique id. No two users can have the same id.", example = "102", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Unique email. No two users can have the same email.", example = "obi@mail.ru", required = true, position = 2)
    private String email;

    @ApiModelProperty(notes = "Individual taxpayer number.", example = "5553332121", required = true, position = 3)
    private String inn;

    @ApiModelProperty(notes = "Online shop's address.", example = "obi@mail.ru", required = true, position = 4)
    private String domainName;
}
