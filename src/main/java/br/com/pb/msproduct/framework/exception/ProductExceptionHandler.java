package br.com.pb.msproduct.framework.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handlerProductNotFoundException(ProductNotFoundException productNotFoundException) {
        ProductException productException = new ProductException(
                productNotFoundException.getMessage(),
                productNotFoundException.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        StandartError error = new StandartError
                (LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Wrong value requested in parameter field", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
