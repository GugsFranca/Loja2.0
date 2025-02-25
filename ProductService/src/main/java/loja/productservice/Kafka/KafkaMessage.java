package loja.productservice.Kafka;

import loja.productservice.entity.products.ProductModel;
import lombok.Builder;

@Builder
public record KafkaMessage(String token, ProductModel model) {
}
