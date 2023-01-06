package br.com.pb.barbershop.msproduct.mocks;

import br.com.pb.barbershop.msproduct.domain.model.Product;

import java.math.BigDecimal;

public class ProductMock {

    public static Product getProductMock() {
        return Product.builder()
                .id(1L)
                .name("Shampoo")
                .description("Shampoo anti-caspa")
                .value(BigDecimal.valueOf(30.0))
                .quantity(15L)
                .build();
    }
}
