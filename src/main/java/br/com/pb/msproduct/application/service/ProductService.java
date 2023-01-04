package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.PageableDTO;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.DataIntegrityValidationException;
import br.com.pb.msproduct.framework.exception.IdNotFoundException;
import br.com.pb.msproduct.framework.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        productDTO.setId(id);
        checkIfNameExists(productDTO);

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

        checkIfNameExists(dto);

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

    private void checkIfNameExists(ProductDTO productDTO) {

        var check = repository.findByNameIgnoreCase(productDTO.getName());
        if (check.isPresent() && productDTO.getId() != check.get().getId()){
            throw new DataIntegrityValidationException("Product Name already exists");
        }
    }
    public ProductDTO findById(Long id) {
        checkIfIdExists(id);
        Product product = repository.findById(id).get();
        return modelMapper.map(product, ProductDTO.class);
    }

    private void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

}

