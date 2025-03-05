package loja.productservice.service.product;

import loja.productservice.entity.products.ProductModel;
import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse findById(Long id);
    List<ProductModel> findAll();
    ProductResponse findByName(String name);
    String addProduct(ProductRequest request);

    ProductResponse sendToCart(String token, Long id, int quantity);
}
