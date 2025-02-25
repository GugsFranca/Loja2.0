package loja.productservice.service.product;

import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse findById(Long id);
    List<ProductResponse> findAll();
    ProductResponse findByName(String name);
    String addProduct(ProductRequest request);
    ProductResponse sendToCart(String token, Long id);
}
