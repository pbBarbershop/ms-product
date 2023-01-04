package br.com.pb.msproduct.framework.adapters.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.pb.msproduct.application.service.ProductService;
import br.com.pb.msproduct.domain.dto.PageableDTO;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private static final String URL = "/product";
    private final String NAME = "Gel Capilar";
    private final BigDecimal VALUE = new BigDecimal("40");
    private final String DESCRIPTION = "Description of product";
    private final Long QUANTITY = 3L;
    Gson gson;

    private static final String ID_URL = "/product/1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void init(){
        gson = new Gson();

    }


    @Test
    void findAll() throws Exception {
        PageableDTO pageableDTO = new PageableDTO();
        when(productService.findAll(any(), any())).thenReturn(pageableDTO);
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
    void updateProduct() throws Exception {
        ProductDTO  productDTO = getProductDTO();
        ProductResponse productResponse = new ProductResponse();
        var request = gson.toJson(productDTO);

        when(productService.updateProduct(any(), any())).thenReturn(productResponse);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private ProductDTO getProductDTO() {
        return ProductDTO.builder().name(NAME)
                .description(DESCRIPTION)
                .value(VALUE)
                .quantity(QUANTITY)
                .build();
    }
}

