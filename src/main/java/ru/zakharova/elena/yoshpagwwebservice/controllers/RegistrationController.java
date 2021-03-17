package ru.zakharova.elena.yoshpagwwebservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharova.elena.yoshpagwwebservice.controllers.utils.ConnectionUtil;
import ru.zakharova.elena.yoshpagwwebservice.dtos.RolesTypeEnum;
import ru.zakharova.elena.yoshpagwwebservice.dtos.token.SystemUser;


@CrossOrigin("https://yoshpa-web-application-service.herokuapp.com")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reg")
@Api("Set of endpoints for shop and customer registration")
public class RegistrationController {


    private final ConnectionUtil connectionUtil;

    // @ApiOperation("Returns new refresh JWT token, new access JWT token, expired date of theirs, customer's id if customer's registration attempt is successful.")
    @ApiOperation("Returns HttpStatus of the customer's registration attempt in customer's database with using login-registration-service.")
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> customerRegistration(@RequestBody @ApiParam("Cannot be empty") SystemUser systemUser) {
        return connectionUtil.registrationRequest(systemUser, RolesTypeEnum.CUSTOMER);
    }

    //@ApiOperation("Returns new refresh JWT token, new access JWT token, expired date of theirs, shop's id if shop's registration attempt is successful.")
    @ApiOperation("Returns HttpStatus of the shop's registration attempt in shop's database with using login-registration-service.")
    @PostMapping(value = "/shop", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> shopRegistration(@RequestBody @ApiParam("Cannot be empty") SystemUser systemUser) {
        return connectionUtil.registrationRequest(systemUser, RolesTypeEnum.SHOP);
    }

}