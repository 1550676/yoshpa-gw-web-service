package ru.zakharova.elena.yoshpagwwebservice.aspect;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.zakharova.elena.yoshpagwwebservice.exception.TokenNotValidException;
import ru.zakharova.elena.yoshpagwwebservice.exception.UnsupportedAuthorizationType;


@Slf4j
@Aspect
@Configuration
@RequiredArgsConstructor
public class TokenValidationAspect {

    @Value("${app.auth-type}")
    private String authType;

    @Value("${app.jwt.secret-key}")
    private String secretKey;

    @Before("execution(* ru.zakharova.elena.yoshpagwwebservice.controllers.ShopController.* (*, java.lang.String))" +
            " || execution(* ru.zakharova.elena.yoshpagwwebservice.controllers.RegistrationController.* (*, java.lang.String))")
    public void tokenCheck(JoinPoint joinPoint) {
        String token = (String) joinPoint.getArgs()[1];
        log.info("Check for token: " + token);
        if (token.startsWith(authType)) {
            token = token.substring(authType.length() + 1);
            checkToken(token);
        } else {
            throw new UnsupportedAuthorizationType("Unsupported authorization type for token " + token);
        }
    }

    public void checkToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new TokenNotValidException("Token " + token + " is not valid: " + e.getMessage());
        }

    }

}
