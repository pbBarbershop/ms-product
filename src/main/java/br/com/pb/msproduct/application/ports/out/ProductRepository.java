package br.com.pb.msproduct.application.ports.out;

import br.com.pb.msproduct.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
