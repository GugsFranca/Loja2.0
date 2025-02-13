package loja.productservice.service;

import loja.productservice.entity.ProductModel;
import loja.productservice.entity.ProductRequest;
import loja.productservice.entity.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public ProductResponse toResponse(ProductModel productModel) {
        return new ProductResponse(
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPreco(),
                productModel.getQuantity());
    }

    public ProductModel toProductModel(ProductRequest request) {
        return ProductModel.builder()
                .name(request.name())
                .description(request.description())
                .preco(request.preco())
                .quantity(request.quantity())
                .build();
    }
}
