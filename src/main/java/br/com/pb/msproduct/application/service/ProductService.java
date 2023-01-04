package br.com.pb.msproduct.application.service;
import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.DataIntegrityValidationException;
import br.com.pb.msproduct.framework.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import br.com.pb.msproduct.domain.dto.PageableDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.pb.msproduct.framework.exception.ObjectNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public void deleteProduct(Long id) {
        checkIfIdExists(id);
        repository.deleteById(id);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductDTO productDTO) {
        checkIfIdExists(id);
        checkIfNameExists(productDTO.getName());

        var product = repository.getReferenceById(id);

        product.setName(productDTO.getName());
        product.setValue(productDTO.getValue());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());

        repository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }
    @Override
    public ProductResponse createProduct(ProductDTO dto) {

        checkIfNameExists(dto.getName());

        Product product = modelMapper.map(dto, Product.class);
        repository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }



    @Override
    public PageableDTO findAll(String name, Pageable pageable) {
        Page page;
        if (name == null || name.trim().length() == 0) {
            page = repository.findAll(pageable);
        } else {
            page = repository.findByName((name.trim()), pageable);
            if (page.isEmpty()) {
                throw new ObjectNotFoundException("Product not found with name: " + name);
            }
        }
        List<ProductDTO> products = page.getContent();
        return PageableDTO
            .builder()
            .numberOfElements(page.getNumberOfElements())
            .totalElements(page.getTotalElements())
            .totalPages(page.getTotalPages())
            .paymentsList(products)
            .build();
    }

    private void checkIfNameExists(String name) {
        var check = repository.findByNameIgnoreCase(name);
        if (check.isPresent()){
            throw new DataIntegrityValidationException("Product Name already exists");
        }
    }

    private void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }
}

