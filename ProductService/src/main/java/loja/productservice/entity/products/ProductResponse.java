package loja.productservice.entity.products;

import loja.productservice.entity.category.CategoryModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ProductModel}
 */
public record ProductResponse(
        String name,
        String description,
        BigDecimal preco,
        int quantity,
        CategoryModel category

) implements Serializable {
}