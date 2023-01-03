package br.com.pb.msproduct.application.ports.in;

import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductUseCase {

    ProductResponse updateProduct(Long id, ProductDTO productDTO);

}
