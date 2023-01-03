package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.PageableDTO;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.framework.exception.ObjectNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository repository;

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
}
