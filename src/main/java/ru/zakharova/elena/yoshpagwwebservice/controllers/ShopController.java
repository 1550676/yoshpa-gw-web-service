package ru.zakharova.elena.yoshpagwwebservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zakharova.elena.yoshpagwwebservice.controllers.utils.ConnectionUtil;
import ru.zakharova.elena.yoshpagwwebservice.dtos.shop.Shop;

import static ru.zakharova.elena.yoshpagwwebservice.controllers.utils.RequestMessageHeaders.JWT_TOKEN_HEADER;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/shops")
@Api("Set of endpoints for registration")
public class ShopController {

    private final ConnectionUtil connectionUtil;

    @ApiOperation("Returns HttpStatus of the attempt to update the store data.")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateShop(@ApiParam("Cannot be empty") @RequestBody Shop shop,
                                        @RequestHeader(JWT_TOKEN_HEADER) String token) {
        return connectionUtil.shopUpdateRequest(shop);
    }

    @ApiOperation("Returns data about the shop by its id.")
    @GetMapping(value = "/info/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getShop(@PathVariable @ApiParam("Cannot be empty") Long id,
                                     @RequestHeader(JWT_TOKEN_HEADER) String token) {
        return connectionUtil.getShopInfoRequest(id);

    }


}