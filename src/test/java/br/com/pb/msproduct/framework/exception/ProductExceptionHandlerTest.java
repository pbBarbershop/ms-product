package br.com.pb.msproduct.framework.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
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
    void idNotFound_Should_Return404() {
        var idNotFound = new IdNotFoundException();
        var httpServletRequestMock = Mockito.mock(HttpServletRequest.class);

        when(httpServletRequestMock.getRequestURI()).thenReturn("teste000");

        var handlerReturn = exceptionHandler.idNotFound(idNotFound, httpServletRequestMock);

        assertEquals(HttpStatus.NOT_FOUND, handlerReturn.getStatusCode());
        assertEquals("teste000", handlerReturn.getBody().getPath());
    }
}
