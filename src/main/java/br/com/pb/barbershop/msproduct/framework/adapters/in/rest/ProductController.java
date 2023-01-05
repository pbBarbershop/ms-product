package br.com.pb.barbershop.msproduct.framework.adapters.in.rest;

import br.com.pb.barbershop.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.barbershop.msproduct.domain.dto.PageableDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductUseCase productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }

    @GetMapping
    public PageableDTO findAll(@RequestParam(required = false) String name, Pageable pageable) {
        return this.productService.findAll(name, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
        @PathVariable Long id,
        @RequestBody @Valid ProductDTO productDTO
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable @NotNull Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
