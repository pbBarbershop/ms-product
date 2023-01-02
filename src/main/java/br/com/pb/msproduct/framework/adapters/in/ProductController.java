package br.com.pb.msproduct.framework.adapters.in;

import br.com.pb.msproduct.application.service.ProductService;
import br.com.pb.msproduct.domain.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.pb.msproduct.domain.dto.ProductDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;




}
