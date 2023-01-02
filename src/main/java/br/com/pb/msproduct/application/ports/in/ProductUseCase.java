package br.com.pb.msproduct.application.ports.in;


import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductUseCase {

    void deleteProduct(Long id);



}
