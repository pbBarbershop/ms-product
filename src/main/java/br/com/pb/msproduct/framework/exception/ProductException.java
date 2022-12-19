package br.com.pb.msproduct.framework.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ProductException {

    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public ProductException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }
}
