package br.com.pb.msproduct.framework.adapters.in;

import br.com.pb.msproduct.application.service.ProductService;
import br.com.pb.msproduct.domain.dto.ProductResponse;
import br.com.pb.msproduct.domain.model.Product;
import jakarta.validation.Valid;
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


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDTO));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
    }




}
