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
                .category(request.category())
                .build();
    }
}
