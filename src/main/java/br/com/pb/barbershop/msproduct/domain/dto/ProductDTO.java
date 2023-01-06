package br.com.pb.barbershop.msproduct.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Campo inválido")
    private String name;

    private String description;

    @NotNull(message = "Campo inválido")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal value;

    @NotNull(message = "Campo inválido")
    @PositiveOrZero
    private Long quantity;
}
