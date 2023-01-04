package br.com.pb.msproduct.application.ports.in;

import br.com.pb.msproduct.domain.dto.PageableDTO;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductUseCase {

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, ProductDTO productDTO);

    ProductResponse createProduct(ProductDTO dto);

    public PageableDTO findAll(String productName, Pageable pageable);

    public ProductDTO findById(Long id);
}

