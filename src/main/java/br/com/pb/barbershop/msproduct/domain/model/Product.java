package br.com.pb.barbershop.msproduct.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo inválido")
    @Pattern(regexp = "^([a-zA-ZãÃéÉíÍóÓêÊôÔáÁ\\s])+$", message = "Campo deve conter apenas letras")
    private String name;

    @Pattern(regexp = "^([a-zA-ZãÃéÉíÍóÓêÊôÔáÁ\\s])+$", message = "Campo deve conter apenas letras")
    private String description;

    @NotNull(message = "Campo inválido")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal value;

    @NotNull(message = "Campo inválido")
    @PositiveOrZero(message = "Valor deve ser maior ou igual a zero")
    private Long quantity;
}
