package br.com.pb.msproduct.domain.dto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private String name;

    private String description;

    private BigDecimal value;
    private Long quantity;
}
