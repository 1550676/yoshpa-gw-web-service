package ru.zakharova.elena.yoshpagwwebservice.exception;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.zakharova.elena.yoshpagwwebservice.exception.utils.ValidationErrorDTO;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> JWTSignatureException(Exception e) {
        log.error("The authenticity of the token has not been confirmed.  \n", e);
        return new ResponseEntity<>("The authenticity of the token was not confirmed.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> resourceNotReadableException(Exception e) {
        log.error("Data in request was incorrect \n" + e);
        String[] exceptionCause = e.getCause().toString().split("\n");
        return new ResponseEntity<>("Data in request was incorrect. " + exceptionCause[0], HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
            return new ResponseEntity<ValidationErrorDTO>(processFieldErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
        for (FieldError fieldError : fieldErrors) {
            dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
            log.info("Error filling in the input field " + fieldError.getField() +
                    ": " + fieldError.getDefaultMessage());
        }
        return dto;
    }

//    @ExceptionHandler(ConnectException.class)
//    public ResponseEntity<StatusResponse> connectionLost(ConnectException e) {
//        log.error("Нет ответа от сервиса", e);
//        return error(INTERNAL_SERVER_ERROR, "Нет соединения");
//    }
//
//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<StatusResponse> tokenError(WebRequest request, JwtException e) {
//        log.error("Ошибка при проверке токена '{}':\n{}", request.getHeader(JWT_TOKEN_HEADER), e.getMessage());
//        return error(BAD_REQUEST, "Ошибка при проверке токена");
//    }
//
//    @ExceptionHandler(AuthorizationException.class)
//    public ResponseEntity<StatusResponse> authError(RuntimeException e) {
//        log.error("Ошибка при aвторизации: {}", e.getMessage());
//        return error(UNAUTHORIZED, e.getMessage());
//    }

    @ExceptionHandler({ResourceNotFoundException.class, TokenNotValidException.class, UnsupportedAuthorizationType.class})
    public ResponseEntity<?> badRequest(RuntimeException e) {
        log.error(String.format(e.getMessage()));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler({InvalidFormatException.class, MethodArgumentNotValidException.class, MismatchedInputException.class, MissingRequestHeaderException.class})
//    public ResponseEntity<StatusResponse> invalidFormat(Exception e) {
//        log.error("Ошибка в запросе", e);
//        return error(BAD_REQUEST, "Ошибка в запросе");
//    }


    @ExceptionHandler
    public ResponseEntity<?> exception(Exception e) {
        log.error("Something went wrong. Please contact technical support.  \n", e);
        return new ResponseEntity<>("Something went wrong. Please contact technical support", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
