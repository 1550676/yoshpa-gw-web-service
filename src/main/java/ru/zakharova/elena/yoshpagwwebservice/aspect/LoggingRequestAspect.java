package ru.zakharova.elena.yoshpagwwebservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LoggingRequestAspect {

    @AfterReturning("execution(* ru.zakharova.elena.yoshpagwwebservice.controllers.ShopController.* (..))")
    public void logRequest(JoinPoint joinPoint) {

        log.info("Method invocation: " + joinPoint.getSignature().getName());
    }
}
