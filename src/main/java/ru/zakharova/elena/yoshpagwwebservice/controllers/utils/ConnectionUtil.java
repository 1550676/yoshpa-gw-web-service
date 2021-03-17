package ru.zakharova.elena.yoshpagwwebservice.controllers.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.zakharova.elena.yoshpagwwebservice.dtos.RolesTypeEnum;
import ru.zakharova.elena.yoshpagwwebservice.dtos.shop.Shop;
import ru.zakharova.elena.yoshpagwwebservice.dtos.token.SystemUser;
import ru.zakharova.elena.yoshpagwwebservice.dtos.token.TokenData;

import java.util.Arrays;

import static ru.zakharova.elena.yoshpagwwebservice.controllers.utils.RequestMessageHeaders.JWT_TOKEN_HEADER;


@Component
@RequiredArgsConstructor
public class ConnectionUtil {

    private final String AUTH_ENDPOINT = "/auth/token";
    private final String SHOP_UPDATE_ENDPOINT = "/shops/update";
    private final String SHOP_INFO_ENDPOINT = "/shops/info/";

    private final String CUSTOMER_NAME_INFO_ENDPOINT = "/shops/info/";
    private final String CUSTOMER_ADDRESS_INFO_ENDPOINT = "/shops/info/";
    private final String CUSTOMER_EMAIL_INFO_ENDPOINT = "/shops/info/";
    private final String CUSTOMER_PHONE_INFO_ENDPOINT = "/shops/info/";

    private final String CUSTOMER_NAME_UPDATE_ENDPOINT = "/shops/info/";
    private final String CUSTOMER_ADDRESS_UPDATE_ENDPOINT = "/shops/info/";
    private final String CUSTOMER_EMAIL_UPDATE_ENDPOINT = "/shops/info/";
    private final String CUSTOMER_PHONE_UPDATE_ENDPOINT = "/shops/info/";

    private final String CUSTOMER_DELETE_ENDPOINT = "/customer/delete/";

    @Value("${app.login-registration-service.host}")
    private String loginRegistrationServiceHost;

    @Value("${app.shop-service.host}")
    private String shopServiceHost;

    @Value("${app.customer-service.host}")
    private String customerServiceHost;


    private final RestTemplate restTemplate;
    private final ThisServiceTokenManager thisServiceTokenManager;

    public ResponseEntity<?> registrationRequest(SystemUser systemUser, RolesTypeEnum type) {
        HttpHeaders headers = getHeader();
        HttpEntity<SystemUser> entity = new HttpEntity<>(systemUser, headers);
//        if (type.equals(RolesTypeEnum.CUSTOMER))
//            return restTemplate.exchange(loginRegistrationServiceHost + CUSTOMER_REGISTRATION_ENDPOINT, HttpMethod.POST, entity, ResponseEntity.class);
//        if (type.equals(RolesTypeEnum.SHOP))
//            return restTemplate.exchange(loginRegistrationServiceHost + SHOP_REGISTRATION_ENDPOINT, HttpMethod.POST, entity, ResponseEntity.class);
        return new ResponseEntity<>("User's role type is not supported here.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> shopUpdateRequest(Shop shop) {
        HttpHeaders headers = getHeader();
        HttpEntity<Shop> entity = new HttpEntity<>(shop, headers);
        return restTemplate.exchange(shopServiceHost + SHOP_UPDATE_ENDPOINT, HttpMethod.POST, entity, ResponseEntity.class);
    }

    public ResponseEntity<?> getShopInfoRequest(Long id) {
        HttpHeaders headers = getHeader();
        HttpEntity<Shop> entity = new HttpEntity<>(headers);
        String endpoint = "{" + shopServiceHost + SHOP_INFO_ENDPOINT + id + "}";
        return restTemplate.exchange(endpoint, HttpMethod.GET, entity, ResponseEntity.class);
    }

    private HttpHeaders getHeader() {
        String token = thisServiceTokenManager.getCurrentToken();
        if (token == null) {
            TokenData tokenData = doTokenDataRequest();
            thisServiceTokenManager.setCurrentToken(tokenData);
            token = tokenData.getToken();
        }
        String authHeader = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(JWT_TOKEN_HEADER, authHeader);
        return headers;
    }


    private TokenData doTokenDataRequest() {
        SystemUser systemUser = thisServiceTokenManager.getSystemUser();
        HttpEntity<SystemUser> entity = new HttpEntity<>(systemUser);
        TokenData tokenData = restTemplate.exchange(loginRegistrationServiceHost + AUTH_ENDPOINT, HttpMethod.POST, entity, TokenData.class).getBody();
        return tokenData;
    }


}
