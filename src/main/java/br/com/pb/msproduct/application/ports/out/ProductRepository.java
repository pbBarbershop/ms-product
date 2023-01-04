package br.com.pb.msproduct.application.ports.out;

import br.com.pb.msproduct.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByNameIgnoreCase(String name);
    Optional<Product> findByName(String name);


}
