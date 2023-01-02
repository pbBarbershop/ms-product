package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.DataIntegrityValidationException;
import br.com.pb.msproduct.framework.exception.IdNotFoundException;
import br.com.pb.msproduct.framework.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository repository;

    private final ModelMapper modelMapper;


    private void checkIfNameExists(ProductDTO productDTO) {
        var check = repository.findByName(productDTO.getName());
        if (check.isPresent()){
            throw new DataIntegrityValidationException("Product Name already exists");
        }
    }

    public ProductResponse createProduct(ProductDTO dto) {

        checkIfNameExists(dto);

        Product product = modelMapper.map(dto, Product.class);
        repository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }


}


