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

}
