package br.com.pb.msproduct.framework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(value ={ProductNotFoundException.class,})
    public ResponseEntity<Object> handlerProductNotFoundException(ProductNotFoundException productNotFoundException) {
        ProductException productException = new ProductException(
                productNotFoundException.getMessage(),
                productNotFoundException.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
