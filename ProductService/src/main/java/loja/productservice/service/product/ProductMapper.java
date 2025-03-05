package loja.productservice.service.product;

import loja.productservice.entity.products.ProductModel;
import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public ProductResponse toResponse(ProductModel productModel) {
        return new ProductResponse(
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPreco(),
                productModel.getQuantity(),
                productModel.getCategory()
        );
    }

    public ProductModel toProductModel(ProductRequest request) {
        return ProductModel.builder()
                .name(request.name())
                .description(request.description())
                .preco(request.preco())
                .quantity(request.quantity())
                .build();
    }

    public ProductModel toSendForCart(ProductModel product, int quantity) {
        return ProductModel.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .preco(product.getPreco())
                .quantity(quantity)
                .category(product.getCategory())
                .build();
    }
}
