package br.com.pb.msproduct.application.ports.in;

import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.application.service.ProductService;
import br.com.pb.msproduct.domain.dto.PageableDTO;
import br.com.pb.msproduct.domain.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductUseCase {

    public PageableDTO findAll(String productName, Pageable pageable);

}
