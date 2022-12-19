package br.com.pb.msproduct.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ProductId;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String ProductDescription;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal ProductValor;


}
