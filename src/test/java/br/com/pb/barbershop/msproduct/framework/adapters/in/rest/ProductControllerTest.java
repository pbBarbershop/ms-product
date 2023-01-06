package br.com.pb.barbershop.msproduct.framework.adapters.in.rest;

import br.com.pb.barbershop.msproduct.application.service.ProductService;
import br.com.pb.barbershop.msproduct.domain.dto.PageableDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private static final String URL = "/product";

    private static final String ID_URL = "/product/1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        ProductDTO productDTO = getProductDTO();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setName("Shampoo");
        productResponse.setDescription("Shampoo anti-caspa");
        productResponse.setValue(BigDecimal.valueOf(30.0));
        productResponse.setQuantity(15L);

        when(service.create(productDTO)).thenReturn(productResponse);
        String json = objectMapper.writeValueAsString(productDTO);

        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post(URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    @Test
    void findAll() throws Exception {
        PageableDTO pageableDTO = new PageableDTO();
        when(service.findAll(any(), any())).thenReturn(pageableDTO);
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get(URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        var paymentDTO = new ProductDTO();
        when(service.findById(any())).thenReturn(paymentDTO);
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get(ID_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        ProductDTO productDTO = getProductDTO();
        ProductResponse productResponse = new ProductResponse();
        var request = objectMapper.writeValueAsString(productDTO);

        when(service.update(any(), any())).thenReturn(productResponse);
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put(ID_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        doNothing().when(service).delete(1L);

        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete(ID_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    private ProductDTO getProductDTO() {
        return ProductDTO.builder()
                .name("Shampoo")
                .description("Shampoo anti-caspa")
                .value(BigDecimal.valueOf(30.0))
                .quantity(15L).build();
    }
}
