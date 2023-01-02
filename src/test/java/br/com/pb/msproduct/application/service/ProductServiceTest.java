package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.IdNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void shouldFindProductById() {
        Product product = new Product();
        ProductDTO productDTO = new ProductDTO();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        ProductDTO result = productService.findById(1L);

        assertEquals(productDTO, result);
    }

    @Test
    void shouldReturnHandledExceptionIdNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> productService.findById(1L));

        verify(productRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(modelMapper);
    }

}