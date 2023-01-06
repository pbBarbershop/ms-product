package br.com.pb.barbershop.msproduct.application.service;

import br.com.pb.barbershop.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.barbershop.msproduct.domain.dto.PageableDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductResponse;
import br.com.pb.barbershop.msproduct.domain.model.Product;
import br.com.pb.barbershop.msproduct.framework.adapters.out.repository.ProductJpaRepository;
import br.com.pb.barbershop.msproduct.framework.exception.GenericException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductJpaRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public ProductResponse create(ProductDTO dto) {
        var name = dto.getName().trim();
        dto.setName(name);

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
                throw new GenericException(HttpStatus.BAD_REQUEST, "Produto com nome " + name + " não foi encontrado.");
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

    @Override
    public ProductDTO findById(Long id) {
        checkIfIdExists(id);
        Product product = repository.findById(id).get();
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductResponse update(Long id, ProductDTO productDTO) {
        Product product = repository
                .findById(id)
                .orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "Id não encontrado!"));

        checkIfNameExists(productDTO);

        product.setName(productDTO.getName());
        product.setValue(productDTO.getValue());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());

        repository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public void delete(Long id) {
        checkIfIdExists(id);
        repository.deleteById(id);
    }

    private void checkIfNameExists(ProductDTO productDTO) {
        var check = repository.findByNameIgnoreCase(productDTO.getName());
        if (check.isPresent() && productDTO.getId() != check.get().getId()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Nome do produto já existe");
        }
    }

    private void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "Id não encontrado!"));
    }
}
