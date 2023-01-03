package br.com.pb.msproduct.application.ports.in;

import br.com.pb.msproduct.domain.dto.ProductDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ProductUseCase {
    public ProductDTO findById(Long id);
}
