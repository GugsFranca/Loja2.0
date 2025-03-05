package loja.cartservice.service;

import loja.cartservice.entity.CartEntity;
import loja.cartservice.entity.CartEntityResponse;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public CartEntityResponse toEntityResponse(CartEntity cartEntity) {
        return new CartEntityResponse(
                cartEntity.getClientId(),
                cartEntity.getCartLine(),
                cartEntity.getQuantity(),
                cartEntity.getTotalAmount());
    }
}
