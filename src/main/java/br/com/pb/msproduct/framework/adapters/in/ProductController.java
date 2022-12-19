package br.com.pb.msproduct.framework.adapters.in;

import br.com.pb.msproduct.application.service.ProductService;
import br.com.pb.msproduct.domain.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{ProductId}")
    public Product getProductDetails(String ProductId) {
        return product;
    }

    // Todos os detalhes do produto no banco de dados
    @GetMapping("/")
    public Product getAllProductDetails() {
        return product;
    }

    @PostMapping("/")
    public String createProductDetails(@RequestBody Product Product) {
        this.product = product;
        return "Product Created Successfully";
    }

    @PutMapping("/")
    public String updateProductDetails(@RequestBody Product Product) {
        this.product = product;
        return "Product Updated Successfully";
    }

    @DeleteMapping("/{ProductId}")
    public String deleteProductDetails(String ProductId) {
        this.product = product;
        return "Product Deleted Successfully";
    }


}
