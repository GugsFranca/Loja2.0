package loja.productservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ProductModel}
 */
public record ProductResponse(
        String name,
        String description,
        BigDecimal preco,
        int quantity) implements Serializable {
}