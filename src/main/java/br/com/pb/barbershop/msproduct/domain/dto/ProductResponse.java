package br.com.pb.barbershop.msproduct.domain.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private String name;
    private String description;
    private BigDecimal value;
    private Long quantity;
}