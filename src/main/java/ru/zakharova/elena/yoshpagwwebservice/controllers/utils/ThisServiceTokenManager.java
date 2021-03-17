package ru.zakharova.elena.yoshpagwwebservice.controllers.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.zakharova.elena.yoshpagwwebservice.dtos.token.SystemUser;
import ru.zakharova.elena.yoshpagwwebservice.dtos.token.TokenData;

import java.util.Date;

@Component
@Data
public class ThisServiceTokenManager {

    @Value("${app.service-email}")
    private String thisServiceEmail;

    @Value("${app.service-password}")
    private String thisServicePassword;
    private final RestTemplate restTemplate;


    private String currentToken;
    private Date currentTokenExpiredDate;

    private SystemUser systemUser;

    public String getCurrentToken() {
        if (currentToken != null && currentTokenExpiredDate.getTime() < new Date().getTime()) {
            currentToken = null;
        }
        return currentToken;
    }

    public void setCurrentToken(TokenData tokenData) {
            this.currentToken = tokenData.getToken();
            this.currentTokenExpiredDate = tokenData.getTokenExpiredDate();
    }

    public SystemUser getSystemUser() {
        if (this.systemUser == null)
        this.systemUser = new SystemUser(thisServiceEmail, thisServicePassword);
        return systemUser;
    }


}
