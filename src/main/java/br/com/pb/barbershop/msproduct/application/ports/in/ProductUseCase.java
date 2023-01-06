package br.com.pb.barbershop.msproduct.application.ports.in;

import br.com.pb.barbershop.msproduct.domain.dto.PageableDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductUseCase {
    ProductResponse create(ProductDTO dto);

    public PageableDTO findAll(String productName, Pageable pageable);

    public ProductDTO findById(Long id);

    ProductResponse update(Long id, ProductDTO productDTO);

    void delete(Long id);
}


