package br.com.pb.msproduct.application.service;

import br.com.pb.msproduct.domain.model.Product;

import java.util.List;

public interface ProductService {

    public String createProduct(Product product);
    public String updateProduct(Product product);
    public String deleteProduct(String productId);
    public Product getProduct(String productId);
    public List<Product> getAllProducts();

}
