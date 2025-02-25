package loja.cartservice.kafka.entity;

import java.math.BigDecimal;

public record ProductModel(
        String name,
        String description,
        BigDecimal preco,
        int quantity,
        CategoryModel category

) {
}
