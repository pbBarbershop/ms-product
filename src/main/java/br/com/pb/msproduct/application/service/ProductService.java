package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.framework.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;


}


