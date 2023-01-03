package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static final Long ID = 1l;
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;
    @Mock
    private ModelMapper modelMapper;



    @Test
    void whenUpdateShouldSucess(){
        var product = new Product();
        var productResponse = new ProductResponse();
        var productDTO = new ProductDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(product));
        Mockito.when(repository.save(any())).thenReturn(product);
        Mockito.when(modelMapper.map(any(), eq(ProductResponse.class))).thenReturn(productResponse);

        ProductResponse response = service.updateProduct(ID, productDTO);
        Assertions.assertEquals(response, productResponse);
        verify(repository).save(any());

    }

}
