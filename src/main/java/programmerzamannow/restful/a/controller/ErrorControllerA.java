package programmerzamannow.restful.a.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.a.model.WebResponseA;
import programmerzamannow.restful.model.WebResponse;

@RestControllerAdvice
public class ErrorControllerA {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponseA<String>> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponseA.<String>builder().errors(exception.getMessage()).build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponseA<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponseA.<String>builder().errors(exception.getReason()).build());
    }
}
