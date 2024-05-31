package com.enigma.jdbc.repository;

import com.enigma.jdbc.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product saveProduct(Product product);
    Product findProductById(Integer Id);
    List<Product> findAllProducts();
    Product updateProduct(Product product);
    void deleteProduct(Integer Id);


}
