package br.com.pb.msproduct.domain.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductDTO {

    @NotBlank(message = "invalid field")
    private String name;

    private String description;

    @NotNull(message = "invalid field")
    @Positive(message = "value must be positive")
    private BigDecimal valor;

    @NotNull(message = "invalid field")
    @PositiveOrZero
    private Long quantity;

}
