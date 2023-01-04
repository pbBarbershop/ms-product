package br.com.pb.msproduct.application.service;
import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;

import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.DataIntegrityValidationException;
import br.com.pb.msproduct.framework.exception.IdNotFoundException;
import br.com.pb.msproduct.framework.exception.ProductNotFoundException;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository repository;

    private final ModelMapper modelMapper;



    @Override
    public ProductResponse updateProduct(Long id, ProductDTO productDTO) {
        checkIdExists(id);
        checkIfNameExists(productDTO.getName());

        var product = repository.getReferenceById(id);

        product.setName(productDTO.getName());
        product.setValue(productDTO.getValue());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());

        repository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    public ProductResponse createProduct(ProductDTO dto) {

        checkIfNameExists(dto.getName());

        Product product = modelMapper.map(dto, Product.class);
        repository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    private void checkIfNameExists(String name) {
        var check = repository.findByNameIgnoreCase(name);
        if (check.isPresent()){
            throw new DataIntegrityValidationException("Product Name already exists");
        }
    }

    private void checkIdExists(Long id){
        var productOptional = repository.findById(id);

        if (!productOptional.isPresent()){
            throw new IdNotFoundException(id);
        }
    }

}


