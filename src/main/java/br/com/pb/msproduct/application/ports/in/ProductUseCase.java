package br.com.pb.msproduct.application.ports.in;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductUseCase {

    void deleteProduct(Long id);


}
