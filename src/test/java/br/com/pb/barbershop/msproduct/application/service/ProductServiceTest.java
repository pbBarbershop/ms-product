package br.com.pb.barbershop.msproduct.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import br.com.pb.barbershop.msproduct.domain.dto.PageableDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductResponse;
import br.com.pb.barbershop.msproduct.domain.model.Product;
import br.com.pb.barbershop.msproduct.framework.adapters.out.repository.ProductJpaRepository;
import br.com.pb.barbershop.msproduct.framework.exception.GenericException;
import java.util.Arrays;
import java.util.Optional;

import br.com.pb.barbershop.msproduct.mocks.ProductMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductJpaRepository repository;

    @Spy
    private ModelMapper mapper;

    private static final Long ID=1L;
    @Test
    void shouldCreateProduct() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Shampoo");
        Product product = new Product();
        when(mapper.map(productDTO, Product.class)).thenReturn(product);
        when(mapper.map(product, ProductResponse.class)).thenReturn(new ProductResponse());
        when(repository.save(product)).thenReturn(product);

        service.create(productDTO);

        verify(repository).save(product);
        verify(mapper).map(productDTO, Product.class);
        verify(mapper).map(product, ProductResponse.class);
        assertEquals("Shampoo", productDTO.getName());
    }

    @Test
    void shouldTryCreate_AndThen_ThrowExceptionNameAlreadyExists() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Shampoo");
        when(repository.findByNameIgnoreCase(anyString())).thenReturn(Optional.of(ProductMock.getProductMock()));

        assertThrows(GenericException.class, () -> service.create(productDTO));
    }

    @Test
    void shouldfindAll_whenNameIsNull() {
        Pageable pageable = mock(Pageable.class);

        Page<Product> page = mock(Page.class);
        when(page.getContent()).thenReturn(Arrays.asList(new Product()));
        when(page.getNumberOfElements()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(page.getTotalPages()).thenReturn(1);

        when(repository.findAll(pageable)).thenReturn(page);

        PageableDTO result = service.findAll(null, pageable);

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

        when(repository.findAll(pageable)).thenReturn(page);

        PageableDTO result = service.findAll("", pageable);

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

        when(repository.findByName(name, pageable)).thenReturn(page);

        PageableDTO result = service.findAll(name, pageable);

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

        when(repository.findByName(name.trim(), pageable)).thenReturn(emptyPage);

        assertThrows(GenericException.class, () -> service.findAll(name, pageable));
    }

    @Test
    void shouldFindProductById() {
        Product product = new Product();
        ProductDTO productDTO = new ProductDTO();
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        when(mapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        ProductDTO result = service.findById(1L);

        assertEquals(productDTO, result);
    }

    @Test
    void shouldReturnHandledExceptionIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.findById(1L));
    }

    @Test
    void shouldUpdate_And_ReturnSuccess() {
        ProductDTO productDTO = new ProductDTO();
        Product product = new Product();
        when(repository.findById(any())).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);

        ProductResponse response = service.update(ID, productDTO);
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getValue(), product.getValue());
    }

    @Test
    void shouldTryUpdateThen_ThrowException_WhenIdNotFound() {
        ProductDTO productDTO = new ProductDTO();
        when(repository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.update(ID, productDTO));
    }

    @Test
    void shouldDelete_And_ReturnSuccess() {
        Product product = new Product();
        when(repository.findById(ID)).thenReturn(Optional.of(product));
        doNothing().when(repository).deleteById(ID);

        service.delete(ID);
        verify(repository).deleteById(ID);
    }

}
