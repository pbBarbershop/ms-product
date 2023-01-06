package br.com.pb.barbershop.msproduct.framework.adapters.out.repository;

import br.com.pb.barbershop.msproduct.application.ports.out.ProductRepositoryPortOut;
import br.com.pb.barbershop.msproduct.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryPortOut {

    private final ProductJpaRepository repository;

    @Override
    public Optional<Product> findByNameIgnoreCase(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public Page findByName(String name, Pageable pageable) {
        return repository.findByName(name, pageable);
    }
}
