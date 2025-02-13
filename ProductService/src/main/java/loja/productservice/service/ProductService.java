package loja.productservice.service;

import loja.productservice.entity.ProductRequest;
import loja.productservice.entity.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse findById(Long id);
    List<ProductResponse> findAll();
    ProductResponse findByName(String name);
    String addProduct(ProductRequest request);
    ProductResponse sendToCart(Long id);





}
