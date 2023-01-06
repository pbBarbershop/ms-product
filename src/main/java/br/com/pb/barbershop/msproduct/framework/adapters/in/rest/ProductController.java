package br.com.pb.barbershop.msproduct.framework.adapters.in.rest;

import br.com.pb.barbershop.msproduct.application.ports.in.ProductUseCase;
import br.com.pb.barbershop.msproduct.domain.dto.PageableDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductDTO;
import br.com.pb.barbershop.msproduct.domain.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductUseCase productService;

    @Operation(summary = "Cadastrar produto")
    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }

    @Operation(summary = "Listar produtos")
    @GetMapping
    public PageableDTO findAll(@RequestParam(required = false) String name, Pageable pageable) {
        return this.productService.findAll(name, pageable);
    }

    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @Operation(summary = "Atualizar produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
        @PathVariable Long id,
        @RequestBody @Valid ProductDTO productDTO
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productDTO));
    }

    @Operation(summary = "Excluir produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable @NotNull Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
