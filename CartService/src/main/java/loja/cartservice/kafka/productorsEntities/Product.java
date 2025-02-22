package loja.cartservice.kafka.productorsEntities;

import java.math.BigDecimal;

public record Product(
        String name,
        String description,
        BigDecimal preco,
        int quantity,
        CategoryModel category

) {
}
