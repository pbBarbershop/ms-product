package br.com.pb.msproduct.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandartError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}