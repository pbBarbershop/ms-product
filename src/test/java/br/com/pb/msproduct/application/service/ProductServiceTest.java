package br.com.pb.msproduct.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.domain.dto.PageableDTO;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.model.Product;
import br.com.pb.msproduct.framework.exception.ObjectNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void shouldfindAll_whenNameIsNull() {
        Pageable pageable = mock(Pageable.class);

        Page<Product> page = mock(Page.class);
        when(page.getContent()).thenReturn(Arrays.asList(new Product()));
        when(page.getNumberOfElements()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);

        when(productRepository.findAll(pageable)).thenReturn(page);

        PageableDTO result = productService.findAll(null, pageable);

        assertEquals(1, result.getNumberOfElements());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getPaymentsList().size());
    }

    @Test
    public void shouldFindAll_whenNameIsEmptyString() {
        Pageable pageable = mock(Pageable.class);

        Page<Product> page = mock(Page.class);
        when(page.getContent()).thenReturn(Arrays.asList(new Product()));
        when(page.getNumberOfElements()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);

        when(productRepository.findAll(pageable)).thenReturn(page);

        PageableDTO result = productService.findAll("", pageable);

        assertEquals(1, result.getNumberOfElements());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getPaymentsList().size());
    }

    @Test
    void shouldFindAll_whenNameIsNotNull() {
        String name = "shampoo";
        Pageable pageable = mock(Pageable.class);

        Page<Product> page = mock(Page.class);
        when(page.getContent()).thenReturn(Arrays.asList(new Product()));
        when(page.getNumberOfElements()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);

        when(productRepository.findByName(name, pageable)).thenReturn(page);

        PageableDTO result = productService.findAll(name, pageable);

        assertEquals(1, result.getNumberOfElements());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getPaymentsList().size());
    }

    @Test
    void shouldThrow_ObjectNotFoundException_whenNameDoesNotExist() {
        String name = "invalidName";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyPage = Page.empty();

        when(productRepository.findByName(name.trim(), pageable)).thenReturn(emptyPage);

        assertThrows(ObjectNotFoundException.class, () -> productService.findAll(name, pageable));
    }
}
