package loja.productservice.entity.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import loja.productservice.entity.category.CategoryModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ProductModel}
 */
public record ProductRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Description is required")
        String description,
        @NotNull
        @PositiveOrZero(message = "Price should be positive")
        BigDecimal preco,
        @PositiveOrZero int quantity,
        @NotNull
        CategoryModel category

) implements Serializable {
}