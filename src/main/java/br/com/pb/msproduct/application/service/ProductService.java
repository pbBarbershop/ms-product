package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository repository;

    private final ModelMapper modelMapper;

    private void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }
    @Override
    public void deleteProduct(Long id) {
        checkIfIdExists(id);
        repository.deleteById(id);
    }

}


