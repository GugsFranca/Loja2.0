package loja.cartservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class CartEntity {

    @Id
    private String id;
    private Long clientId;
    private List<CartLine> cartLine = new ArrayList<>();
    private int quantity;
    private Double totalAmount;
}
