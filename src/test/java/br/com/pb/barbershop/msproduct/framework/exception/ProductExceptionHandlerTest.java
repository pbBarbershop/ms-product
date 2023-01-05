package br.com.pb.barbershop.msproduct.framework.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ProductExceptionHandlerTest {

    @InjectMocks
    private ProductExceptionHandler exceptionHandler;

    @Test
    void productNotFound_Should_ReturnException() {
        ObjectNotFoundException productNotFoundException = new ObjectNotFoundException(
            "Product not found with name: shampoo"
        );

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/test");
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(productNotFoundException, request);

        StandardError error = response.getBody();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), error.getStatus());
        assertEquals("Product not found with name: shampoo", error.getError());
        assertEquals("/test", error.getPath());
    }

    @Test
    void idNotFound_Should_Return404() {
        var idNotFound = new IdNotFoundException(null);
        var httpServletRequestMock = Mockito.mock(HttpServletRequest.class);

        when(httpServletRequestMock.getRequestURI()).thenReturn("teste000");

        var handlerReturn = exceptionHandler.idNotFound(idNotFound, httpServletRequestMock);

        assertEquals(HttpStatus.NOT_FOUND, handlerReturn.getStatusCode());
        assertEquals("teste000", handlerReturn.getBody().getPath());
    }
}
