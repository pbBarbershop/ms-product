package br.com.pb.barbershop.msproduct.framework.adapters.out.repository;

import br.com.pb.barbershop.msproduct.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase(String name);

    Page findByName(String name, Pageable pageable);
}
