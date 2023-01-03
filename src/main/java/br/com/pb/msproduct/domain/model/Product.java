package br.com.pb.msproduct.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "invalid field")
    private String name;

    private String description;

    @NotNull(message = "invalid field")
    @Positive(message = "value must be positive")
    private BigDecimal value;

    @NotNull(message = "invalid field")
    @PositiveOrZero
    private Long quantity;
}
