package br.com.pb.msproduct.application.service;
import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.DataIntegrityValidationException;
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
        var productOptional = repository.findById(id);

        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not exists");
        }

        var product = productOptional.get();
        var product2 = repository.findByNameIgnoreCase(productDTO.getName());;

        if(product2.isPresent() && product2.get().getId() != id){
            throw new DataIntegrityValidationException("Already product this name exists");
        }

        product.setName(productDTO.getName());
        product.setValue(productDTO.getValue());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());

        repository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }
}


