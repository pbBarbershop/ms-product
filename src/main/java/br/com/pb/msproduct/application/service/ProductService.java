package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.PageableDTO;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.ProductNotFoundException;
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
    public PageableDTO findAll(String name, Pageable pageable) {
        Page page;
        if (name == null || name.trim().length() == 0) {
            page = repository.findAll(pageable);
        } else {
            page = repository.findByName((name.trim()), pageable);
        }
        List<ProductDTO> products = page.getContent();
        return PageableDTO.builder().numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages()).paymentsList(products).build();
    }
}


