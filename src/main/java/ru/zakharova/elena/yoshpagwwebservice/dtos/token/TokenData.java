package ru.zakharova.elena.yoshpagwwebservice.dtos.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "Class representing token and its expired date.")
public class TokenData {

    @ApiModelProperty(notes = "Token for users.", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJyb2xlIjpbIlNIT1AiXSwiZXhwIjoxNjE0NDM2Njc5LCJpYXQiOjE2MTQzNTAyNzl9.7p2sLsZUoJS2dXs8-xuIknhR6HDWAivwsik17yIkSxk",
            required = true, position = 1)
    private String token;

    @ApiModelProperty(notes = "Expired date of token in ms.", example = "124234149563000", required = true, position = 2)
    private Date tokenExpiredDate;


}
