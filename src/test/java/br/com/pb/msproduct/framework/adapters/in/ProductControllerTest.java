package br.com.pb.msproduct.framework.adapters.in;

import br.com.pb.msproduct.application.service.ProductService;
import br.com.pb.msproduct.domain.dto.ProductDTO;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.beans.BeanInfo;
import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ProductService service;

    Gson gson;

    private final String NAME = "Gel Capilar";
    private final BigDecimal VALUE = new BigDecimal("40");
    private final String DESCRIPTION = "Description of product";

    private final Long QUANTITY = 3L;

    private static final String ID_URL = "/product/1";
    @BeforeEach
    void init(){
        gson = new Gson();
    }
    @Test
    void updateProduct() throws Exception {
        ProductDTO  productDTO = getProductDTO();
        ProductResponse productResponse = new ProductResponse();
        var request = gson.toJson(productDTO);

        when(service.updateProduct(any(), any())).thenReturn(productResponse);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(ID_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private ProductDTO getProductDTO() {
       return ProductDTO.builder().name(NAME)
               .description(DESCRIPTION)
               .value(VALUE)
               .quantity(QUANTITY)
               .build();
    }

}
