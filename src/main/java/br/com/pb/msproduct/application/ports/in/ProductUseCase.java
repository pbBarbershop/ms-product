package br.com.pb.msproduct.application.ports.in;

import br.com.pb.msproduct.application.ports.out.ProductRepository;
import br.com.pb.msproduct.application.service.ProductService;
import br.com.pb.msproduct.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUseCase implements ProductService {

    ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public String createProduct(Product product) {
        //Lógica de Negócios
        productRepository.save(product);
        return "Success";
    }

    @Override
    public String updateProduct(Product product) {
        //Lógica de Negócios
        productRepository.save(product);
        return "Success";
    }

    @Override
    public String deleteProduct(String productId) {
        //Lógica de Negócios
        productRepository.deleteById(productId);
        return "Success";
    }

    @Override
    public Product getProduct(String productId) {
        //Lógica de Negócios
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getAllProducts() {
        //Lógica de Negócios
        return productRepository.findAll();
    }
}
