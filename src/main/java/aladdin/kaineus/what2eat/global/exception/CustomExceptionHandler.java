package aladdin.kaineus.what2eat.global.exception;

import aladdin.kaineus.what2eat.global.response.ErrorResponseBody;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseBody handleCustomException(CustomException e) {
        LOGGER.error("CustomException", e);
        return ErrorResponseBody.of(e.getErrorCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseBody handleInvalidCustomException(ConstraintViolationException e) {
        LOGGER.error("ConstraintViolationException", e);
        return ErrorResponseBody.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("HttpRequestMethodNotSupportedException", e);
        return ResponseEntity.badRequest().body("해당 endpoint 를 찾지 못했습니다. method 와 url 을 확인해주세요");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handleValidationException(MethodArgumentNotValidException e) {
        LOGGER.error("Validation Exception", e);
        List<ValidationError> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            ValidationError build = ValidationError.builder()
                    .fieldName(fieldName)
                    .errorMessage(errorMessage)
                    .build();
            errors.add(build);
        });
        return errors;
    }

}
