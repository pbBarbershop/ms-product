package br.com.pb.msproduct.application.ports.in;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import br.com.pb.msproduct.domain.dto.PageableDTO;

@Service
public interface ProductUseCase {

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, ProductDTO productDTO);

    ProductResponse createProduct(ProductDTO dto);

    public PageableDTO findAll(String productName, Pageable pageable);
}
