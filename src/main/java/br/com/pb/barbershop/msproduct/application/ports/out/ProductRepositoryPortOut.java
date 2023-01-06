package br.com.pb.barbershop.msproduct.application.ports.out;

import br.com.pb.barbershop.msproduct.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepositoryPortOut {
    Optional<Product> findByNameIgnoreCase(String name);

    Page findByName(String name, Pageable pageable);
}
