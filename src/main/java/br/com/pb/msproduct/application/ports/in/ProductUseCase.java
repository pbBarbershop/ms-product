package br.com.pb.msproduct.application.ports.in;

import br.com.pb.msproduct.domain.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductUseCase {

    public ProductDTO findById(Long id);
}
