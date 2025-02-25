package loja.cartservice.entity;


import loja.cartservice.kafka.entity.ProductModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document
public class CartLine {

    @Id
    private String id;
    private ProductModel productModel;

    public CartLine(ProductModel productModel) {
        this.productModel = productModel;
    }
}
